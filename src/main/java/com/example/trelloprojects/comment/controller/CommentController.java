package com.example.trelloprojects.comment.controller;

import com.example.trelloprojects.comment.dto.CommentRequestDto;
import com.example.trelloprojects.comment.service.CommentService;
import com.example.trelloprojects.common.dto.MsgResponseDto;
import com.example.trelloprojects.user.entity.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comment")
public class CommentController {
  private final CommentService commentService;

  @PostMapping
  public ResponseEntity<MsgResponseDto> createComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CommentRequestDto requestDto, @RequestParam Long cardId) {
    commentService.createComment(userDetails.getUser(), requestDto, cardId);
    return ResponseEntity.ok().body(new MsgResponseDto("댓글 추가 성공", HttpStatus.CREATED.value()));
  }

  @PutMapping
  public ResponseEntity<MsgResponseDto> editComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CommentRequestDto requestDto, @RequestParam Long commentId) {
    commentService.editComment(userDetails.getUser(), requestDto, commentId);
    return ResponseEntity.ok().body(new MsgResponseDto("댓글 수정 성공", HttpStatus.OK.value()));
  }

  @DeleteMapping
  public ResponseEntity<MsgResponseDto> deleteComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam Long commentId) {
    commentService.deleteComment(userDetails.getUser(), commentId);
    return ResponseEntity.ok().body(new MsgResponseDto("댓글 삭제 성공", HttpStatus.OK.value()));
  }
}
