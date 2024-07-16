package com.forohub.foro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.forohub.foro.model.Comment;


public interface ICommentRepository extends JpaRepository<Comment, Integer> {
}
