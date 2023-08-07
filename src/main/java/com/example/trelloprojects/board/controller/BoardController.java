package com.example.trelloprojects.board.controller;


import com.example.trelloprojects.board.dto.BoardRequestDto;
import com.example.trelloprojects.board.dto.BoardResponseDto;
import com.example.trelloprojects.board.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;


    //shift + f6
    //Board 생성
    @PostMapping
    public BoardResponseDto createBoard(BoardRequestDto requestDto){
        return boardService.createBoard(requestDto);}

    //특정 Board 조회
    @GetMapping("/board")
    public BoardResponseDto getOneBoard(Long id){
        return boardService.getOneBoard(id);
        }

    //BoardName 변경
    @PutMapping("/board")
    public BoardResponseDto changeBoardName(Long id, BoardRequestDto requestDto){
        return boardService.changeBoardName(id,requestDto);
    }

    //BoardDescription 변경
    @PutMapping
    public BoardResponseDto changeBoardDescription(Long id,BoardRequestDto requestDto){
        return boardService.changeBoardDescription(id,requestDto);
    }

    //Board 삭제
    @DeleteMapping
    public void deleteBoard(Long id){
        boardService.deleteBoard(id);
    }
    }


