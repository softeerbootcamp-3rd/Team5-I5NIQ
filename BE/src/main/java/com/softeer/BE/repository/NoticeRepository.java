package com.softeer.BE.repository;

import com.softeer.BE.domain.entity.Notice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Query("SELECT n " +
            "FROM Notice n " +
            "ORDER BY id DESC")
    List<Notice> findAllByOrderByIdDesc(Pageable pageable);

    @Query("SELECT n " +
            "FROM Notice n " +
            "WHERE id < :id " +
            "ORDER BY id DESC")
    List<Notice> findByIdLessThanOrderByIdDesc(Long id, Pageable pageable);
}
