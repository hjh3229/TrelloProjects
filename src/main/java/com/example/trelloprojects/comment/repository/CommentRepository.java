package com.example.trelloprojects.comment.repository;

import com.example.trelloprojects.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
