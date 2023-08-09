package com.example.trelloprojects.columns.entity;

import com.example.trelloprojects.board.entity.Board;
import com.example.trelloprojects.card.entity.Card;
import com.example.trelloprojects.columns.dto.AddColumnsRequest;
import com.example.trelloprojects.columns.dto.UpdateColumnsRequest;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Columns {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Long position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "columns")
    private List<Card> cardList = new ArrayList<>();

    public Columns(AddColumnsRequest request, Long position, Board board) {
        this.name = request.getName();
        this.position = position;
        this.board = board;
    }

    public Columns update(UpdateColumnsRequest request) {
        this.name = request.getName();
        return this;
    }

    public void setPosition(Long position) {
        this.position = position;
    }
}
