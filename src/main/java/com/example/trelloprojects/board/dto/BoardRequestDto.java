package com.example.trelloprojects.board.dto;

import com.example.trelloprojects.board.entity.ColorEnum;
import com.example.trelloprojects.workspace.entity.Workspace;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardRequestDto {
    private String name;
    private String description;
    private ColorEnum color;
    private Workspace workspace;
}
