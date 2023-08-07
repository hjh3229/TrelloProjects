package com.example.trelloprojects.card.dto;

import com.example.trelloprojects.card.entity.Card;
import java.time.LocalDateTime;
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

  public CardResponseDto(Card card) {
    this.id = card.getId();
    this.title = card.getTitle();
    this.description = card.getDescription();
    this.color = card.getColor();
    this.deadLine = card.getDeadLine();
  }
}
