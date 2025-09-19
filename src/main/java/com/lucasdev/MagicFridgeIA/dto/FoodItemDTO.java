package com.lucasdev.MagicFridgeIA.dto;

import com.lucasdev.MagicFridgeIA.enums.Categoria;
import java.time.LocalDate;

public class FoodItemDTO {

    private Long id;
    private String nome;
    private Categoria categoria;
    private Integer quantidade;
    private LocalDate validade;
}
