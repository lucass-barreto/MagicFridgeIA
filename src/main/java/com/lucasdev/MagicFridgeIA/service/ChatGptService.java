package com.lucasdev.MagicFridgeIA.service;

import com.lucasdev.MagicFridgeIA.dto.FoodItemDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ChatGptService {

    private final WebClient webClient;
    private final FoodItemService foodItemService;

    @Value("${chatgpt.api.key}")
    private String chatGptApiKey;

    public ChatGptService(WebClient webClient, FoodItemService foodItemService) {
        this.webClient = webClient;
        this.foodItemService = foodItemService;
    }

    public Mono<String> generateRecipe(){

        List<FoodItemDTO> foodItemDTOS = foodItemService.listarRegistros();

        String alimentos = foodItemDTOS.stream()
                .map(item -> String.format("%s (quantidade: %d - Validade: %s)", item.getNome(), item.getQuantidade(), item.getValidade()))
                .collect(Collectors.joining(", "));

        String prompt = "Crie uma receita deliciosa priorizando ingredientes que combinam bem e excluindo os que passaram da validade. Não precisa usar todos." +
                " Seja direto e forneça o nome da receita, os ingredientes a serem usados e o modo de preparo. Envie sua resposta sem Markdown." +
                " Use os seguintes ingredientes: " + alimentos;

        System.out.println(prompt);

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-4o-mini",
                "messages", List.of(
                        Map.of("role", "system", "content", "Você é um chefe de cozinha"),
                        Map.of("role", "user", "content", prompt)
                ));


        return webClient.post()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + chatGptApiKey)
                .bodyValue(requestBody)
                .retrieve()
                .bodyToMono(Map.class)
                .map(response -> {
                   var choicesList = (List<Map<String, Object>>) response.get("choices");

                   if (choicesList != null && !choicesList.isEmpty()) {
                       Map<String, Object> firstChoice = choicesList.get(0);
                       Map message = (Map) firstChoice.get("message");

                       return message.get("content").toString();
                   } return "Nenhuma receita foi gerada";

                });
    }
}



