package com.example.trelloprojects.columns.controller;

import com.example.trelloprojects.card.dto.CardResponseDto;
import com.example.trelloprojects.card.service.CardService;
import com.example.trelloprojects.columns.dto.AddColumnsRequest;
import com.example.trelloprojects.columns.dto.ReorderRequest;
import com.example.trelloprojects.columns.dto.UpdateColumnsRequest;
import com.example.trelloprojects.columns.entity.Columns;
import com.example.trelloprojects.columns.service.impl.ColumnsServiceImpl;
import com.example.trelloprojects.common.dto.MsgResponseDto;
import lombok.RequiredArgsConstructor;
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

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ColumnsController {

    private final ColumnsServiceImpl columnsServiceImpl;
    private final CardService cardService;

    @PostMapping("/column/{boardId}")
    public ResponseEntity<MsgResponseDto> addColumns(@PathVariable Long boardId,
            @RequestBody AddColumnsRequest request) {
        columnsServiceImpl.addColumns(boardId, request);
        return ResponseEntity.ok().body(new MsgResponseDto("컬럼 생성 성공", HttpStatus.OK.value()));
    }

    @PutMapping("/column/{columnId}")
    public ResponseEntity<MsgResponseDto> updateColumns(@PathVariable Long columnId,
            @RequestBody UpdateColumnsRequest request) {
        columnsServiceImpl.updateColumns(columnId, request);
        return ResponseEntity.ok().body(new MsgResponseDto("컬럼 수정 성공", HttpStatus.OK.value()));
    }

    @DeleteMapping("/column/{columnId}")
    public ResponseEntity<MsgResponseDto> deleteColumns(@PathVariable Long columnId) {
        columnsServiceImpl.deleteColumns(columnId);
        return ResponseEntity.ok().body(new MsgResponseDto("컬럼 삭제 성공", HttpStatus.OK.value()));
    }

    @GetMapping("/column/card/{cardId}")
    public ResponseEntity<CardResponseDto> getCard(@PathVariable Long cardId) {
        return ResponseEntity.ok().body(new CardResponseDto(cardService.findCard(cardId)));
    }

    @PutMapping("/column/{columnId}/reorder")
    public ResponseEntity<MsgResponseDto> reorder(@PathVariable Long columnId,
            @RequestBody ReorderRequest request) {
        columnsServiceImpl.reorder(columnId, request);
        return ResponseEntity.ok().body(new MsgResponseDto("컬럼 순서 재정렬", HttpStatus.OK.value()));
    }

}
