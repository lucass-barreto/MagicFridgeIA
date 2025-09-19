package com.lucasdev.MagicFridgeIA.controller;

import com.lucasdev.MagicFridgeIA.dto.FoodItemDTO;
import com.lucasdev.MagicFridgeIA.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }


    public ResponseEntity<FoodItemDTO> registrarProduto (@RequestBody FoodItemDTO foodItemDTO){
        FoodItemDTO novoRegistroDTO = foodItemService.registrarProduto(foodItemDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(novoRegistroDTO);
    }

    public ResponseEntity<List<FoodItemDTO>> listarRegistros(){
        List<FoodItemDTO> todosRegistrosDTO = foodItemService.listarRegistros();
        return ResponseEntity.ok(todosRegistrosDTO);
    }

    public ResponseEntity<?> listarPorId(@PathVariable Long id){
        FoodItemDTO itemEncontradoDTO = foodItemService.listarPorId(id);

        if(itemEncontradoDTO == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        } return ResponseEntity.ok(itemEncontradoDTO);
    }

    public ResponseEntity<?> deletarPorId (@PathVariable Long id){
        if (foodItemService.deletarPorId(id)){
            return ResponseEntity.noContent()
                    .build();
        } return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .build();
    }

    public ResponseEntity<FoodItemDTO> alterarItem(@PathVariable Long id, @RequestBody FoodItemDTO foodItemAtualizadoDTO){
        FoodItemDTO itemAlteradoDTO = foodItemService.alterarItem(id,foodItemAtualizadoDTO);

        if (itemAlteradoDTO == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .build();
        } return ResponseEntity.ok(itemAlteradoDTO);
    }
}
