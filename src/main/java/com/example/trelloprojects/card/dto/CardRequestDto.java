package com.example.trelloprojects.card.dto;

import com.example.trelloprojects.common.entity.ColorEnum;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardRequestDto {
  private String title;
  private String description;
  private ColorEnum color;
  private LocalDateTime deadLine;
}
