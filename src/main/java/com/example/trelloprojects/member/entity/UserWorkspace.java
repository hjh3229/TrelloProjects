package com.example.trelloprojects.member.entity;

import com.example.trelloprojects.member.dto.MemberResponseDto;
import com.example.trelloprojects.user.entity.User;
import com.example.trelloprojects.workspace.entity.Workspace;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "user_workspace")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserWorkspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Workspace workspace;

    private Boolean isAdmin = false;

    public UserWorkspace(User user, Workspace workspace) {
        this.user = user;
        this.workspace = workspace;
    }

    public void updateAdminRole(Boolean admin) {
        this.isAdmin = admin;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public MemberResponseDto toMemberResponseDto() {
        return MemberResponseDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .admin(isAdmin)
                .build();
    }
}
