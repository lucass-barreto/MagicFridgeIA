package com.lucasdev.MagicFridgeIA.controller;

import com.lucasdev.MagicFridgeIA.dto.FoodItemDTO;
import com.lucasdev.MagicFridgeIA.enums.Categoria;
import com.lucasdev.MagicFridgeIA.service.ChatGptService;
import com.lucasdev.MagicFridgeIA.service.FoodItemService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/ui")
public class RecipeControllerUI {

    private FoodItemService foodItemService;
    private ChatGptService chatGptService;

    public RecipeControllerUI(FoodItemService foodItemService, ChatGptService chatGptService) {
        this.foodItemService = foodItemService;
        this.chatGptService = chatGptService;
    }

    @GetMapping("/dashboard")
    public String exibirPaginaPrincipal(Model model) {
        model.addAttribute("items", foodItemService.listarRegistros());
        model.addAttribute("newItem", new FoodItemDTO());
        model.addAttribute("categorias", Categoria.values());
        model.addAttribute("recipe", null);
        return "dashboard";
    }

    @PostMapping("/food/adicionar")
    public String adicionarItem(@ModelAttribute("newItem") FoodItemDTO newItem) {
        foodItemService.registrarProduto(newItem);
        return "redirect:/ui/dashboard";
    }

    @DeleteMapping("/food/deletar/{id}")
    public String deletarItem(@PathVariable Long id) {
        foodItemService.deletarPorId(id);
        return "redirect:/ui/dashboard";
    }

    @GetMapping("/recipe/generate")
    public String gerarReceita(Model model) {
        String receitaGerada = chatGptService.generateRecipe().block();
        model.addAttribute("recipe", receitaGerada);
        model.addAttribute("items", foodItemService.listarRegistros());
        model.addAttribute("newItem", new FoodItemDTO());
        model.addAttribute("categorias", Categoria.values());
        return "dashboard";
    }

    @GetMapping("/food/alterar/{id}")
    public String exibirPaginaDeAlteracao(@PathVariable Long id, Model model) {
        Optional<FoodItemDTO> itemOpcional = foodItemService.buscarPorId(id);

        if (itemOpcional.isPresent()) {
            model.addAttribute("item", itemOpcional.get());
            model.addAttribute("categorias", Categoria.values());
            return "alterar-item";
        }

        return "redirect:/ui/dashboard";
    }

    @PutMapping("/food/alterar/{id}")
    public String alterarItem(@PathVariable Long id, @ModelAttribute("item") FoodItemDTO itemAtualizado) {
        foodItemService.alterarItem(id, itemAtualizado);
        return "redirect:/ui/dashboard";
    }
}