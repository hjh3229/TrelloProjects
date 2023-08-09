package com.example.trelloprojects.card.dto;

import com.example.trelloprojects.common.entity.ColorEnum;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
public class CardRequestDto {

    private String title;
    private String description;
    private ColorEnum color;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime deadLine;

}
