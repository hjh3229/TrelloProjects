package com.example.trelloprojects.card.service;

import com.example.trelloprojects.card.dto.CardRequestDto;
import com.example.trelloprojects.card.dto.CardResponseDto;
import com.example.trelloprojects.card.entity.Card;
import com.example.trelloprojects.card.repository.CardRepository;
import com.example.trelloprojects.colum.entity.Colum;
import com.example.trelloprojects.colum.repository.ColumRepository;
import com.example.trelloprojects.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardService {
  private final CardRepository cardRepository;
  private final ColumRepository columRepository;

  public CardResponseDto createCard(CardRequestDto requestDto, Long columnId) {
    Colum colum = findColumn(columnId);
    Card card = cardRepository.save(new Card(requestDto, colum));
    return new CardResponseDto(card);
  }

  private Colum findColumn(Long columnId) {
    return columRepository.findById(columnId).orElseThrow(() ->
        new IllegalArgumentException("존재하지 않는 컬럼입니다.")
    );
  }
}
