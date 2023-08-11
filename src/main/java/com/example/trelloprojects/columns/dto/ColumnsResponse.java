package com.example.trelloprojects.columns.dto;

import com.example.trelloprojects.card.dto.CardResponseDto;
import com.example.trelloprojects.columns.entity.Columns;
import java.util.List;
import lombok.Getter;

@Getter
public class ColumnsResponse {

    private String name;
    private List<CardResponseDto> cardList;

    public ColumnsResponse(Columns columns) {
        this.name = columns.getName();
        this.cardList = columns.getCardList().stream().map(CardResponseDto::new).toList();
    }

}
