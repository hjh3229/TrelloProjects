package com.example.trelloprojects.card.controller;

import com.example.trelloprojects.common.dto.MsgResponseDto;
import com.example.trelloprojects.card.dto.CardCommentResponseDto;
import com.example.trelloprojects.card.dto.CardRequestDto;
import com.example.trelloprojects.card.dto.CardResponseDto;
import com.example.trelloprojects.card.service.CardService;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CardController {

  private final CardService cardService;

  @PostMapping("/card")
  public ResponseEntity<CardResponseDto> createCard(@RequestParam Long columnId, @RequestBody CardRequestDto requestDto) {
    CardResponseDto result = cardService.createCard(requestDto, columnId);
    return ResponseEntity.status(HttpStatus.CREATED).body(result);
  }

  @GetMapping("/card")
  public ResponseEntity<CardCommentResponseDto> getComments(@RequestParam Long cardId) {
    CardCommentResponseDto result = cardService.getComments(cardId);
    return ResponseEntity.status(HttpStatus.OK).body(result);
  }

  @PutMapping("/card/title")
  public ResponseEntity<MsgResponseDto> editTitle(@RequestParam Long cardId, @RequestParam String title) {
    cardService.editTitle(cardId, title);
    return ResponseEntity.ok().body(new MsgResponseDto("카드 제목 수정 성공", HttpStatus.OK.value()));
  }

  @PutMapping("/card/description")
  public ResponseEntity<MsgResponseDto> editDescription(@RequestParam Long cardId, @RequestParam String description) {
    cardService.editDescription(cardId, description);
    return ResponseEntity.ok().body(new MsgResponseDto("카드 설명 수정 성공", HttpStatus.OK.value()));
  }

  @PutMapping("/card/deadline")
  public ResponseEntity<MsgResponseDto> editDeadLine(@RequestParam Long cardId, @RequestParam LocalDateTime deadLine) {
    cardService.editDeadLine(cardId, deadLine);
    return ResponseEntity.ok().body(new MsgResponseDto("카드 마감일 수정 성공", HttpStatus.OK.value()));
  }

  @PostMapping("/card/with")
  public ResponseEntity<MsgResponseDto> setMember(@RequestParam String username, @RequestParam Long cardId) {
    cardService.setMember(username, cardId);
    return ResponseEntity.ok().body(new MsgResponseDto("작업자 할당/취소 성공", HttpStatus.OK.value()));
  }

  @DeleteMapping("/card/{cardId}")
  public ResponseEntity<MsgResponseDto> deleteCard(@PathVariable Long cardId) {
    cardService.deleteCard(cardId);
    return ResponseEntity.ok().body(new MsgResponseDto("카드 삭제 성공", HttpStatus.OK.value()));
  }

}
