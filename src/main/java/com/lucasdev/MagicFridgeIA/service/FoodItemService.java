package com.lucasdev.MagicFridgeIA.service;

import com.lucasdev.MagicFridgeIA.dto.FoodItemDTO;
import com.lucasdev.MagicFridgeIA.dto.mapper.FoodItemMapper;
import com.lucasdev.MagicFridgeIA.model.FoodItem;
import com.lucasdev.MagicFridgeIA.repository.FoodItemRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FoodItemService {

    private final FoodItemMapper foodItemMapper;
    private FoodItemRepository foodItemRepository;

    public FoodItemService(FoodItemRepository foodItemRepository, FoodItemMapper foodItemMapper) {
        this.foodItemRepository = foodItemRepository;
        this.foodItemMapper = foodItemMapper;
    }

    public FoodItemDTO registrarProduto(FoodItemDTO foodItemDTO) {
        FoodItem foodItem = foodItemMapper.map(foodItemDTO);
        return foodItemMapper.map(foodItemRepository.save(foodItem));
    }

    public List<FoodItemDTO> listarRegistros(){
        List<FoodItem> listaRegistros = foodItemRepository.findAll();
        return listaRegistros.stream()
                .map(foodItemMapper :: map)
                .collect(Collectors.toList());
    }

    public Optional<FoodItemDTO> buscarPorId(Long id){
        Optional<FoodItem> itemProcuradoModel = foodItemRepository.findById(id);

        if (itemProcuradoModel.isPresent()){
            FoodItem itemEncontradoModel = itemProcuradoModel.get();
            FoodItemDTO itemEncontradoDTO = foodItemMapper.map(itemEncontradoModel);
            return Optional.of(itemEncontradoDTO);
        } return Optional.empty();
    }

    public boolean deletarPorId(Long id){
        Optional<FoodItem> itemProcurado = foodItemRepository.findById(id);

        if(itemProcurado.isPresent()){
            foodItemRepository.deleteById(id);
            return true;
        } return false;
    }

    public FoodItemDTO alterarItem (Long id, FoodItemDTO foodItemDTOAtualizado){
        Optional<FoodItem> foodItemProcuradoModel = foodItemRepository.findById(id);

        if (foodItemProcuradoModel.isPresent()){
            FoodItem foodItemAtualizadoModel = foodItemMapper.map(foodItemDTOAtualizado);
            foodItemAtualizadoModel.setId(id);

            return foodItemMapper.map(foodItemRepository.save(foodItemAtualizadoModel));
        } return null;
    }
}
