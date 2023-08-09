package com.example.trelloprojects.member.repository;

import com.example.trelloprojects.member.entity.Invitation;
import com.example.trelloprojects.member.enums.InvitationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {

    Optional<Invitation> findByInviteCodeAndStatus(String inviteCode, InvitationStatus invitationStatus);
}
