package com.example.trelloprojects.invitation.repository;

import com.example.trelloprojects.invitation.entity.Invitation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {

}
