package com.example.trelloprojects.board.dto;

import com.example.trelloprojects.board.entity.Board;
import com.example.trelloprojects.columns.dto.ColumnsResponse;
import com.example.trelloprojects.comment.dto.CommentResponseDto;
import com.example.trelloprojects.common.entity.ColorEnum;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardColumnResponseDto {
  private Long id;
  private String name;
  private String description;
  private ColorEnum color;
  private List<ColumnsResponse> columns;

  public BoardColumnResponseDto(Board board) {
    this.id = board.getId();
    this.name = board.getName();
    this.description = board.getDescription();
    this.color = board.getColor();
    this.columns = board.getColumns().stream().map(ColumnsResponse::new).toList();
  }
}
