package com.example.trelloprojects.user.entity;



import com.example.trelloprojects.user.dto.AddUserRequest;
import com.example.trelloprojects.user.dto.UpdateEmailRequest;

import com.example.trelloprojects.member.dto.MemberResponseDto;

import com.example.trelloprojects.user_card.entity.UserCard;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Table(name = "user")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;
  
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<UserCard> userCards = new ArrayList<>();

    public User(AddUserRequest request, String password) {
        this.username = request.getUsername();
        this.password = password;
        this.email = request.getEmail();
        this.role = UserRoleEnum.USER;
    }

    public void updateEmail(UpdateEmailRequest request) {
        this.email = request.getEmail();
    }
    public MemberResponseDto toMemberResponseDto() {
        return MemberResponseDto.builder()
                .id(this.id)
                .username(this.username)
                .email(this.email)
                .build();

    }
}
