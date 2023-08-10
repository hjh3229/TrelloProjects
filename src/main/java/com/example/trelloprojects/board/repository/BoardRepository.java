package com.example.trelloprojects.board.repository;

import com.example.trelloprojects.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByWorkspaceId(Long workspaceId);
}
