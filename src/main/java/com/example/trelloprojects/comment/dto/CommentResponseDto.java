package com.example.trelloprojects.comment.dto;

import com.example.trelloprojects.comment.entity.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentResponseDto {
  private Long id;
  private String username;
  private String content;

  public CommentResponseDto(Comment comment) {
    this.id = comment.getId();
    this.username = comment.getUsername();
    this.content = comment.getContent();
  }
}
