package com.example.trelloprojects.member.repository;

import com.example.trelloprojects.member.entity.UserWorkspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserWorkspaceRepository extends JpaRepository<UserWorkspace, Long> {
    Optional<UserWorkspace> findByUserIdAndWorkspaceId(Long userId, Long workspaceId);
}
