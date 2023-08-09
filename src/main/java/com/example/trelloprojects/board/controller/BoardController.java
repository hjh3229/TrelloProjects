package com.example.trelloprojects.board.controller;


import com.example.trelloprojects.board.dto.ApiResponseDto;
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

    public ResponseEntity<ApiResponseDto> createBoard(@RequestBody BoardRequestDto requestDto){
         boardService.createBoard(requestDto);
    return ResponseEntity.ok(new ApiResponseDto("회원가입 완료",200));}


    //특정 Board 조회-> board 안에 모든 컬럼이 보이는 것으로
    @GetMapping("/board")
    public ResponseEntity<BoardResponseDto> getOneBoard(@RequestParam Long id){
        return ResponseEntity.ok().body(boardService.getOneBoard(id));
    }

    //BoardName 변경ㅍ
    @PutMapping("/board/{board_id}")
    public ResponseEntity<ApiResponseDto> changeBoardName(@RequestParam Long id, @RequestBody BoardRequestDto requestDto){
       boardService.changeBoardName(id,requestDto);
       return ResponseEntity.ok().body(new ApiResponseDto("보드 이름수정 완료", HttpStatus.CREATED.value()));
    }

    //BoardDescription 변경
    @PutMapping

    public ResponseEntity<ApiResponseDto> changeBoardDescription(@RequestParam Long id, @RequestBody BoardRequestDto requestDto){
        boardService.changeBoardDescription(id,requestDto);
        return ResponseEntity.ok().body(new ApiResponseDto("보드 설명수정 완료", HttpStatus.CREATED.value()));
    }


    //Board 삭제
    @DeleteMapping

    public ResponseEntity<ApiResponseDto> deleteBoard(Long id){
        boardService.deleteBoard(id);
        return ResponseEntity.ok().body(new ApiResponseDto("보드 삭제 완료", HttpStatus.CREATED.value()));
    }
}


