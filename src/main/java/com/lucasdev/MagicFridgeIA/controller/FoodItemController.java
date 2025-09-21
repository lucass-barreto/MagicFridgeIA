package com.lucasdev.MagicFridgeIA.controller;

import com.lucasdev.MagicFridgeIA.dto.FoodItemDTO;
import com.lucasdev.MagicFridgeIA.service.FoodItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    private FoodItemService foodItemService;

    public FoodItemController(FoodItemService foodItemService) {
        this.foodItemService = foodItemService;
    }

    @PostMapping("/adicionar")
    public ResponseEntity<FoodItemDTO> registrarProduto (@RequestBody FoodItemDTO foodItemDTO){
        FoodItemDTO novoRegistroDTO = foodItemService.registrarProduto(foodItemDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(novoRegistroDTO);
    }

    @GetMapping("/")
    public ResponseEntity<List<FoodItemDTO>> listarRegistros(){
        List<FoodItemDTO> todosRegistrosDTO = foodItemService.listarRegistros();
        return ResponseEntity.ok(todosRegistrosDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        Optional<FoodItemDTO> itemEncontradoDTO = foodItemService.buscarPorId(id);

        if (itemEncontradoDTO.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(itemEncontradoDTO);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<?> deletarPorId (@PathVariable Long id){
        if (foodItemService.deletarPorId(id)){
            return ResponseEntity.noContent()
                    .build();
        } return ResponseEntity.notFound().build();
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<FoodItemDTO> alterarItem(@PathVariable Long id, @RequestBody FoodItemDTO foodItemNovoDTO){
        Optional<FoodItemDTO> itemEncontradoDTO = foodItemService.buscarPorId(id);

        if (itemEncontradoDTO.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            FoodItemDTO foodItemAtualizadoDTO = foodItemService.alterarItem(id, foodItemNovoDTO);
            return ResponseEntity.ok(foodItemAtualizadoDTO);
        }
    }
}
