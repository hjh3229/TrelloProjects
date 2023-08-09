package com.example.trelloprojects.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponseDto {
    private String msg;
    private int statusCode;
}
