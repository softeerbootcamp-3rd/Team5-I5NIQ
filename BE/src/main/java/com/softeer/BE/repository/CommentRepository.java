package com.softeer.BE.repository;

import com.softeer.BE.domain.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {
  @Query("select c from comment c join fetch c.user cu where c.program.id=:program_id")
  List<Comment> findCommentsByProgramId(@Param("program_id")long programId);
}
