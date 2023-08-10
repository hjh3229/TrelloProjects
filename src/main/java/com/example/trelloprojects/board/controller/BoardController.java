package com.example.trelloprojects.board.controller;


import com.example.trelloprojects.board.dto.BoardColumnResponseDto;
import com.example.trelloprojects.board.dto.BoardRequestDto;
import com.example.trelloprojects.board.dto.UpdateBoardColor;
import com.example.trelloprojects.board.dto.UpdateBoardDescription;
import com.example.trelloprojects.board.dto.UpdateBoardName;
import com.example.trelloprojects.board.service.BoardService;
import com.example.trelloprojects.common.dto.MsgResponseDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;

    //Board 생성

    @PostMapping("/boards/{workspaceId}")
    public ResponseEntity<MsgResponseDto> createBoard(@RequestBody BoardRequestDto requestDto,
            @PathVariable Long workspaceId) {
        boardService.createBoard(requestDto, workspaceId);
        return ResponseEntity.ok(new MsgResponseDto("회원가입 완료", 200));
    }


    //특정 Board 조회-> board 안에 모든 컬럼이 보이는 것으로
    @GetMapping("/boards/{boardId}")
    public ResponseEntity<BoardColumnResponseDto> getOneBoard(@PathVariable Long boardId) {
        return ResponseEntity.ok().body(boardService.getOneBoard(boardId));
    }

    //BoardName 변경ㅍ
    @PutMapping("/boards/{boardId}/name")
    public ResponseEntity<MsgResponseDto> changeBoardName(@PathVariable Long boardId,
            @RequestBody UpdateBoardName requestDto) {
        boardService.changeBoardName(boardId, requestDto);
        return ResponseEntity.ok()
                .body(new MsgResponseDto("보드 이름수정 완료", HttpStatus.CREATED.value()));
    }

    //BoardDescription 변경
    @PutMapping("/boards/{boardId}/description")
    public ResponseEntity<MsgResponseDto> changeBoardDescription(@PathVariable Long boardId,
            @RequestBody UpdateBoardDescription requestDto) {
        boardService.changeBoardDescription(boardId, requestDto);
        return ResponseEntity.ok()
                .body(new MsgResponseDto("보드 설명수정 완료", HttpStatus.CREATED.value()));
    }

    @PutMapping("/boards/{boardId}/color")
    public ResponseEntity<MsgResponseDto> changeBoardDescription(@PathVariable Long boardId,
            @RequestBody UpdateBoardColor requestDto) {
        boardService.changeBoardColor(boardId, requestDto);
        return ResponseEntity.ok()
                .body(new MsgResponseDto("보드 컬러수정 완료", HttpStatus.CREATED.value()));
    }

    //Board 삭제

    @DeleteMapping("/boards/{boardId}")
    public ResponseEntity<MsgResponseDto> deleteBoard(@PathVariable Long boardId) {
        boardService.deleteBoard(boardId);
        return ResponseEntity.ok().body(new MsgResponseDto("보드 삭제 완료", HttpStatus.CREATED.value()));
    }
}


