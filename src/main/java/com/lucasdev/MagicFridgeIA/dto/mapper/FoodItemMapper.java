package com.lucasdev.MagicFridgeIA.dto.mapper;

import com.lucasdev.MagicFridgeIA.dto.FoodItemDTO;
import com.lucasdev.MagicFridgeIA.model.FoodItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FoodItemMapper {

    FoodItemDTO map(FoodItem foodItem);
    FoodItem map(FoodItemDTO foodItemDTO);
}
