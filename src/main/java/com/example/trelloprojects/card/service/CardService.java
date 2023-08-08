package com.example.trelloprojects.card.service;

import com.example.trelloprojects.card.dto.CardCommentResponseDto;
import com.example.trelloprojects.card.dto.CardRequestDto;
import com.example.trelloprojects.card.dto.CardResponseDto;
import com.example.trelloprojects.card.entity.Card;
import com.example.trelloprojects.card.repository.CardRepository;
import com.example.trelloprojects.columns.entity.Columns;
import com.example.trelloprojects.columns.repository.ColumnsRepository;
import com.example.trelloprojects.user.entity.User;
import com.example.trelloprojects.user.repository.UserRepository;
import com.example.trelloprojects.user_card.entity.UserCard;
import com.example.trelloprojects.user_card.repository.UserCardRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final ColumnsRepository columRepository;
    private final UserRepository userRepository;
    private final UserCardRepository userCardRepository;

    public CardResponseDto createCard(CardRequestDto requestDto, Long columnId) {
        Columns colum = findColumn(columnId);
        Card card = cardRepository.save(new Card(requestDto, colum));
        return new CardResponseDto(card);
    }

    @Transactional(readOnly = true)
    public CardCommentResponseDto getComments(Long cardId) {
        return new CardCommentResponseDto(findCard(cardId));
    }

    @Transactional
    public void editTitle(Long cardId, String title) {
        Card card = findCard(cardId);
        card.setTitle(title);
    }

    @Transactional
    public void editDescription(Long cardId, String description) {
        Card card = findCard(cardId);
        card.setDescription(description);
    }

    @Transactional
    public void editDeadLine(Long cardId, LocalDateTime deadLine) {
        Card card = findCard(cardId);
        card.setDeadLine(deadLine);
    }

    @Transactional
    public void setMember(String username, Long cardId) {
        User user = findUser(username);
        Card card = findCard(cardId);
        UserCard userCard = userCardRepository.findByUserAndCard(user, card).orElse(null);

        if (userCard == null) {
            userCard = new UserCard(user, card);
            userCardRepository.save(userCard);
        } else {
            userCard.cancel();
            userCardRepository.delete(userCard);
        }
    }

    private Columns findColumn(Long columnId) {
        return columRepository.findById(columnId).orElseThrow(() ->
            new IllegalArgumentException("존재하지 않는 컬럼입니다.")
        );
    }

    private Card findCard(Long cardId) {
        return cardRepository.findById(cardId).orElseThrow(() ->
            new IllegalArgumentException("존재하지 않는 카드입니다.")
        );
    }

    private User findUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->
            new IllegalArgumentException("존재하지 않는 유저입니다.")
        );
    }
}
