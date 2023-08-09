package com.example.trelloprojects.member.entity;

import com.example.trelloprojects.member.enums.InvitationStatus;
import com.example.trelloprojects.user.entity.User;
import com.example.trelloprojects.workspace.entity.Workspace;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table(name = "invitation")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inviter_id")
    private User inviter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;

    @Column
    private String inviteeEmail;

    @Column
    private String inviteCode;

    @Column
    @Enumerated(EnumType.STRING)
    private InvitationStatus status = InvitationStatus.PENDING;

    public Invitation(User inviter, Workspace workspace, String inviteeEmail, String inviteCode) {
        this.inviter = inviter;
        this.workspace = workspace;
        this.inviteeEmail = inviteeEmail;
        this.inviteCode = inviteCode;
    }

    public void markAsAccepted() {
        this.status = InvitationStatus.ACCEPTED;
    }
}
