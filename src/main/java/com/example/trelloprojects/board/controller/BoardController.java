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
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto){
        String name= requestDto.getName();
        return boardService.createBoard(requestDto);}

    //특정 Board 조회-> board 안에 모든 컬럼이 보이는 것으로
    @GetMapping("/board")
    public BoardResponseDto getOneBoard(@RequestParam Long id){
        return boardService.getOneBoard(id);
        }

    //BoardName 변경
    @PutMapping("/board/{board_id}")

    public BoardResponseDto changeBoardName(@RequestParam Long id, @RequestBody BoardRequestDto requestDto){
        return boardService.changeBoardName(id,requestDto);
    }

    //BoardDescription 변경
    @PutMapping
    public BoardResponseDto changeBoardDescription(@RequestParam Long id, @RequestBody BoardRequestDto requestDto){
        return boardService.changeBoardDescription(id,requestDto);
    }

    //Board 삭제
    @DeleteMapping
    public void deleteBoard(Long id){
        boardService.deleteBoard(id);
    }
    }


