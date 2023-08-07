package com.example.trelloprojects.card.entity;

import com.example.trelloprojects.colum.entity.Colum;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
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
    @JoinColumn(name = "list_id")
    private Colum colum;
}
