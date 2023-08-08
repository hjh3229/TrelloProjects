package com.example.trelloprojects.user_card.repository;

import com.example.trelloprojects.card.entity.Card;
import com.example.trelloprojects.user.entity.User;
import com.example.trelloprojects.user_card.entity.UserCard;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserCardRepository extends JpaRepository<UserCard, Long> {

  Optional<UserCard> findByUserAndCard(User user, Card card);
}
