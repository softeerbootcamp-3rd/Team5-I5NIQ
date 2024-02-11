package com.softeer.BE.repository;

import com.softeer.BE.domain.entity.Notice;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Query("SELECT n " +
            "FROM notice n " +
            "WHERE id < :id " +
            "ORDER BY id DESC")
    List<Notice> findByIdLessThanOrderByIdDesc(Long id, Pageable pageable);

    @Query("SELECT COUNT(n) > 0 " +
            "FROM notice n WHERE n.id < :id")
    boolean existsByIdLessThan(Long id);
}
