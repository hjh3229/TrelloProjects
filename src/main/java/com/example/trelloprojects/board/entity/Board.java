package com.example.trelloprojects.board.entity;

import com.example.trelloprojects.board.dto.BoardRequestDto;
import com.example.trelloprojects.board.dto.BoardResponseDto;
import com.example.trelloprojects.workspace.entity.Workspace;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
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

    public Board(BoardRequestDto requestDto) {
        this.name = requestDto.getName();
        this.description = requestDto.getDescription();
        this.color = requestDto.getColor();
    }

    public BoardResponseDto toDto() {
        return BoardResponseDto.builder()
                .id(id)
                .name(name)
                .description(description)
                .color(color)
                .build();
    }

    public void updateName(BoardRequestDto requestDto) {
        this.name = requestDto.getName();
    }

    public void updateDescription(BoardRequestDto requestDto) {
        this.description = requestDto.getDescription();
    }
}
