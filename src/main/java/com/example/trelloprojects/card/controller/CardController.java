package com.example.trelloprojects.card.controller;

import com.example.trelloprojects.card.dto.CardCommentResponseDto;
import com.example.trelloprojects.card.dto.CardRequestDto;
import com.example.trelloprojects.card.dto.CardResponseDto;
import com.example.trelloprojects.card.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

}
