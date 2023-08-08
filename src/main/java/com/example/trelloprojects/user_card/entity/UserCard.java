package com.example.trelloprojects.user_card.entity;

import com.example.trelloprojects.card.entity.Card;
import com.example.trelloprojects.user.entity.User;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user_card")
@NoArgsConstructor
public class UserCard {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "card_id")
  private Card card;

  public UserCard(User user, Card card) {
    this.user = user;
    this.card = card;
    user.getUserCards().add(this);
    card.getUserCards().add(this);
  }

  public void cancel() {
    user.getUserCards().remove(this);
    card.getUserCards().remove(this);
  }

}
