package com.example.trelloprojects.card.dto;

import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardRequestDto {
  private String title;
  private String description;
  private String color;
  private LocalDateTime deadLine;
}
