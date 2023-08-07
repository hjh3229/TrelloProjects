package com.example.trelloprojects.invitation.entity;

import com.example.trelloprojects.user.entity.User;
import com.example.trelloprojects.workspace.entity.Workspace;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    String invitee_email;

    @Column
    String status;

    @ManyToOne
    private User invitee_id;

    @ManyToOne
    @JoinColumn(name = "inviter_id")
    private User inviter_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;
}
