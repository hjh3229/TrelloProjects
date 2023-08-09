package com.example.trelloprojects.user_card.dto;

import com.example.trelloprojects.user_card.entity.UserCard;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCardResponseDto {
  private Long id;
  private String username;

  public UserCardResponseDto(UserCard userCard) {
    this.id = userCard.getId();
    this.username = userCard.getUser().getUsername();
  }
}
