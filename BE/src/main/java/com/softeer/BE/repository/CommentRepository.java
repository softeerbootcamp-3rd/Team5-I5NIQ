package com.softeer.BE.repository;

import com.softeer.BE.domain.entity.Comment;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT c FROM comment c WHERE c.user.id = :userId ORDER BY c.createdAt DESC")
    List<Comment> findRecentCommentByUserId(@Param("userId") String userId, Pageable pageable);

}
