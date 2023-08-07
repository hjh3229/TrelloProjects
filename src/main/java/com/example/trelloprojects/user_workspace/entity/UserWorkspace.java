package com.example.trelloprojects.user_workspace.entity;

import com.example.trelloprojects.user.entity.User;
import com.example.trelloprojects.workspace.entity.Workspace;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@Table(name = "user_workspace")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserWorkspace {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;
}
