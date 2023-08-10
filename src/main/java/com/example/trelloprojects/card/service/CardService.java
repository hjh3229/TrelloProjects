package com.example.trelloprojects.card.service;

import com.example.trelloprojects.card.dto.CardCommentResponseDto;
import com.example.trelloprojects.card.dto.CardReorderRequestDto;
import com.example.trelloprojects.card.dto.CardRequestDto;
import com.example.trelloprojects.card.dto.CardResponseDto;
import com.example.trelloprojects.card.entity.Card;
import com.example.trelloprojects.card.repository.CardRepository;
import com.example.trelloprojects.columns.entity.Columns;
import com.example.trelloprojects.columns.repository.ColumnsRepository;
import com.example.trelloprojects.common.error.BusinessException;
import com.example.trelloprojects.common.error.ErrorCode;
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
        Long position = cardRepository.countCardsByColumns(colum);
        Card card = cardRepository.save(new Card(requestDto, colum, position));
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
        UserCard userCard = userCardRepository.findByUserAndCard(user, card).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_CARD_NOT_FOUND)
        );

        if (userCard == null) {
            userCard = new UserCard(user, card);
            userCardRepository.save(userCard);
        } else {
            userCard.cancel();
            userCardRepository.delete(userCard);
        }
    }

    public void deleteCard(Long cardId) {
        Card card = findCard(cardId);
        cardRepository.delete(card);
    }

    private Columns findColumn(Long columnId) {
        return columRepository.findById(columnId).orElseThrow(() ->
                new BusinessException(ErrorCode.COLUMN_NOT_FOUND)
        );
    }

    public Card findCard(Long cardId) {
        return cardRepository.findById(cardId).orElseThrow(() ->
                new BusinessException(ErrorCode.CARD_NOT_FOUND)
        );
    }

    private User findUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new BusinessException(ErrorCode.USER_NOT_FOUND)
        );
    }

    //TO DO
    public void reorderCard(Long cardId, Long columnsId, CardReorderRequestDto reorderRequestDto) {
        Card card = findCard(cardId);

        Columns columns = card.getColumns();
        Long oldPosition = card.getPosition();
        Long newPosition = reorderRequestDto.getPosition();

        if (columns.getId().equals(columnsId)) {

            if (newPosition > oldPosition) {
                cardRepository.decrementAboveToPosition(newPosition, oldPosition,
                        String.valueOf(columns.getId()));
            } else {
                cardRepository.incrementBelowToPosition(newPosition, oldPosition,
                        String.valueOf(columns.getId()));
            }

            card.setPosition(reorderRequestDto.getPosition());
            cardRepository.save(card);

        } else {

            Columns requestcolumns = findColumn(columnsId);

            cardRepository.decrementBelow(card.getPosition(), String.valueOf(columns.getId()));

            Long position = cardRepository.countCardsByColumns(requestcolumns);
            card.setPosition(position);
            card.setColumns(requestcolumns);

            cardRepository.save(card);
        }
    }
}
