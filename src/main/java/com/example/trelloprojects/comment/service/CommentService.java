package com.example.trelloprojects.comment.service;

import com.example.trelloprojects.card.entity.Card;
import com.example.trelloprojects.card.repository.CardRepository;
import com.example.trelloprojects.comment.dto.CommentRequestDto;
import com.example.trelloprojects.comment.entity.Comment;
import com.example.trelloprojects.comment.repository.CommentRepository;
import com.example.trelloprojects.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

  @Transactional
  public void editComment(User user, CommentRequestDto requestDto, Long commentId) {
    Comment comment = findComment(commentId);
    if (comment.getUser().equals(user)) {
      comment.setContent(requestDto.getContent());
    } else {
      throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
    }
  }

  public void deleteComment(User user, Long commentId) {
    Comment comment = findComment(commentId);
    if (comment.getUser().equals(user)) {
      commentRepository.delete(comment);
    } else {
      throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
    }
  }

  private Card findCard(Long cardId) {
    return cardRepository.findById(cardId).orElseThrow(() ->
        new IllegalArgumentException("존재하지 않는 카드입니다.")
    );
  }

  private Comment findComment(Long commentId) {
    return commentRepository.findById(commentId).orElseThrow(() ->
        new IllegalArgumentException("존재하지 않는 댓글입니다.")
    );
  }


}
