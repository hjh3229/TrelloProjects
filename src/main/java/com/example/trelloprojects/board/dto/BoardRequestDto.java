package com.example.trelloprojects.board.dto;

import com.example.trelloprojects.board.entity.ColorEnum;
import lombok.Getter;

@Getter
public class BoardRequestDto {
    private String name;
    private String description;
    private ColorEnum color;
}
