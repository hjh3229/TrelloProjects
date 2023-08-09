package com.example.trelloprojects.board.controller;


import com.example.trelloprojects.board.dto.BoardRequestDto;
import com.example.trelloprojects.board.dto.BoardResponseDto;
import com.example.trelloprojects.board.service.BoardService;
import com.example.trelloprojects.common.dto.MsgResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;


    //shift + f6
    //Board 생성
    @PostMapping
    public ResponseEntity<BoardResponseDto> createBoard(@RequestBody BoardRequestDto requestDto){
        String name= requestDto.getName();
        return ResponseEntity.ok().body(boardService.createBoard(requestDto));
    }

    //특정 Board 조회-> board 안에 모든 컬럼이 보이는 것으로
    @GetMapping("/board")
    public ResponseEntity<BoardResponseDto> getOneBoard(@RequestParam Long id){
        return ResponseEntity.ok().body(boardService.getOneBoard(id));
    }

    //BoardName 변경
    @PutMapping("/board/{board_id}")

    public ResponseEntity<BoardResponseDto> changeBoardName(@RequestParam Long id, @RequestBody BoardRequestDto requestDto){
        return ResponseEntity.ok().body(boardService.changeBoardName(id,requestDto));
    }

    //BoardDescription 변경
    @PutMapping
    public ResponseEntity<BoardResponseDto> changeBoardDescription(@RequestParam Long id, @RequestBody BoardRequestDto requestDto){
        return ResponseEntity.ok().body(boardService.changeBoardDescription(id,requestDto));
    }

    //Board 삭제
    @DeleteMapping
    public ResponseEntity<MsgResponseDto> deleteBoard(Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.ok().body(new MsgResponseDto("보드 삭제 성공", HttpStatus.OK.value()));
    }
}


