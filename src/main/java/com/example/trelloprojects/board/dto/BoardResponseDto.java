package com.example.trelloprojects.board.dto;

import com.example.trelloprojects.board.entity.Board;
import com.example.trelloprojects.common.entity.ColorEnum;
import com.example.trelloprojects.workspace.entity.Workspace;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BoardResponseDto {
    private  Long id;
    private String name;
    private String description;
    private ColorEnum color;
    private Workspace workspace;

    public BoardResponseDto(Board board){
        this.id=board.getId();
        this.name=board.getName();
        this.description=board.getDescription();
        this.color=board.getColor();
        this.workspace = board.getWorkspace();
    }
}

