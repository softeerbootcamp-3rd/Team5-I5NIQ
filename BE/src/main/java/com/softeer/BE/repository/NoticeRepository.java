package com.softeer.BE.repository;

import com.softeer.BE.domain.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NoticeRepository extends JpaRepository<Notice, Long> {

    @Query(value = "select * from notice order by id desc select :size", nativeQuery = true)
    List<Notice> findAllByOrderByIdDesc(Long size);

    @Query(value = "select * from notice where id < :id order by id desc select :size", nativeQuery = true)
    List<Notice> findByIdLessThanOrderByIdDesc(Long id, Long size);
}
