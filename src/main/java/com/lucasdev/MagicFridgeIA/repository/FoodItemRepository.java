package com.lucasdev.MagicFridgeIA.repository;

import com.lucasdev.MagicFridgeIA.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodItemRepository extends JpaRepository <FoodItem, Long> {
}
