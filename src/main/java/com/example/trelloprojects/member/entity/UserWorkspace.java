package com.example.trelloprojects.member.entity;

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

    public UserWorkspace(User user, Workspace workspace) {
        this.user = user;
        this.workspace = workspace;
    }
}
