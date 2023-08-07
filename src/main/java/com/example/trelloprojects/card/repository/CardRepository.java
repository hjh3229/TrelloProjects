package com.example.trelloprojects.card.repository;

import com.example.trelloprojects.card.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<Card, Long> {

}
