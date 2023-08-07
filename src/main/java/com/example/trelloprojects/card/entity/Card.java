package com.example.trelloprojects.card.entity;

import com.example.trelloprojects.columns.entity.Columns;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import com.example.trelloprojects.card.dto.CardRequestDto;
import com.example.trelloprojects.colum.entity.Colum;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "card")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    String title;

    @Column
    String description;

    @Column
    String color;

    @Column
    LocalDateTime deadLine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "column_id")
    private Colum colum;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "card", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    public Card(CardRequestDto requestDto, Colum colum) {
        this.title = requestDto.getTitle();
        this.description = requestDto.getDescription();
        this.color = requestDto.getColor();
        this.deadLine = requestDto.getDeadLine();
        this.colum = colum;
    }
}
