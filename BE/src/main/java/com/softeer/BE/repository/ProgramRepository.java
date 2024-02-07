package com.softeer.BE.repository;

import com.softeer.BE.domain.entity.Program;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProgramRepository extends JpaRepository<Program,Long> {
}
