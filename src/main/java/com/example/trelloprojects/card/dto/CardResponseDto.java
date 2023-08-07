package com.example.trelloprojects.card.dto;

import com.example.trelloprojects.card.entity.Card;
import com.example.trelloprojects.comment.dto.CommentResponseDto;
import com.example.trelloprojects.comment.entity.Comment;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardResponseDto {
  private Long id;
  private String title;
  private String description;
  private String color;
  private LocalDateTime deadLine;
  private List<CommentResponseDto> comments;

  public CardResponseDto(Card card) {
    this.id = card.getId();
    this.title = card.getTitle();
    this.description = card.getDescription();
    this.color = card.getColor();
    this.deadLine = card.getDeadLine();
    this.comments = card.getComments().stream().map((Comment comment) -> new CommentResponseDto(comment)).toList();
  }
}
