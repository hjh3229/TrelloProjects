package com.example.trelloprojects.comment.service;

import com.example.trelloprojects.card.entity.Card;
import com.example.trelloprojects.card.repository.CardRepository;
import com.example.trelloprojects.columns.repository.ColumnsRepository;
import com.example.trelloprojects.comment.dto.CommentRequestDto;
import com.example.trelloprojects.comment.entity.Comment;
import com.example.trelloprojects.comment.repository.CommentRepository;
import com.example.trelloprojects.user.entity.User;
import com.example.trelloprojects.user.repository.UserRepository;
import com.example.trelloprojects.user_card.repository.UserCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

  private final CardRepository cardRepository;
  private final CommentRepository commentRepository;

  public void createComment(User user, CommentRequestDto requestDto, Long cardId) {
    Card card = findCard(cardId);
    commentRepository.save(new Comment(requestDto, user, card));
  }

  private Card findCard(Long cardId) {
    return cardRepository.findById(cardId).orElseThrow(() ->
        new IllegalArgumentException("존재하지 않는 카드입니다.")
    );
  }
}
