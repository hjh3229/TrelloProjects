package com.example.trelloprojects.columns.dto;

import com.example.trelloprojects.card.entity.Card;
import com.example.trelloprojects.columns.entity.Columns;
import java.util.List;
import lombok.Getter;

@Getter
public class ColumnsResponse {

    private String name;
    private List<Card> cardList;

    public ColumnsResponse(Columns columns) {
        this.name = columns.getName();
        this.cardList = getCardList();
    }

}
