package com.example.trelloprojects.board.entity;

import com.example.trelloprojects.board.dto.ApiResponseDto;
import com.example.trelloprojects.board.dto.BoardRequestDto;
import com.example.trelloprojects.workspace.entity.Workspace;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "board")
    private List<Card> cardList = new ArrayList<>();

    public Board(BoardRequestDto requestDto){
        this.name=requestDto.getName();
        this.description= requestDto.getDescription();
        this.color=requestDto.getColor();
    }


    public ResponseEntity<ApiResponseDto> updateName(BoardRequestDto requestDto) {
        this.name= requestDto.getName();
        ApiResponseDto apiResponseDto = new ApiResponseDto( "이름이 변경되었습니다.", HttpStatus.OK.value());
        return new ResponseEntity<>(apiResponseDto, HttpStatus.OK);
    }
    public void updateDescription(BoardRequestDto requestDto) {
        this.description= requestDto.getDescription();
    }
}
