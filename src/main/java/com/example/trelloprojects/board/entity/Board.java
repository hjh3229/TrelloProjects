package com.example.trelloprojects.board.entity;

import com.example.trelloprojects.board.dto.BoardRequestDto;
import com.example.trelloprojects.board.dto.BoardResponseDto;

import com.example.trelloprojects.board.dto.UpdateBoardColor;
import com.example.trelloprojects.board.dto.UpdateBoardDescription;
import com.example.trelloprojects.board.dto.UpdateBoardName;
import com.example.trelloprojects.card.entity.Card;
import com.example.trelloprojects.columns.entity.Columns;

import com.example.trelloprojects.common.entity.ColorEnum;
import com.example.trelloprojects.workspace.entity.Workspace;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    @Enumerated(value = EnumType.STRING)
    private ColorEnum color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;


    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Columns> columns = new ArrayList<>();

    public Board(BoardRequestDto requestDto, Workspace workspace) {
        this.name = requestDto.getName();
        this.description = requestDto.getDescription();
        this.color = requestDto.getColor();
        this.workspace = workspace;
    }

    public BoardResponseDto toDto() {
        return BoardResponseDto.builder()
                .id(id)
                .name(name)
                .description(description)
                .color(color)
                .build();
    }


    public void updateName(UpdateBoardName requestDto) {
        this.name = requestDto.getName();
    }

    public void updateDescription(UpdateBoardDescription requestDto) {
        this.description = requestDto.getDescription();
    }

    public void updateColor(UpdateBoardColor requestDto) {
        this.color = requestDto.getColor();
    }
}
