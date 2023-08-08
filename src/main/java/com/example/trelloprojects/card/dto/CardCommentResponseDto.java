package com.example.trelloprojects.card.dto;

import com.example.trelloprojects.card.entity.Card;
import com.example.trelloprojects.comment.dto.CommentResponseDto;
import com.example.trelloprojects.comment.entity.Comment;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardCommentResponseDto {
  private List<CommentResponseDto> comments;

  public CardCommentResponseDto(Card card) {
    this.comments = card.getComments().stream().map(CommentResponseDto::new).toList();
  }
}
