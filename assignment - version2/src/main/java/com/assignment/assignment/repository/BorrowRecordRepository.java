package com.assignment.assignment.repository;

import com.assignment.assignment.entity.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecord,Integer> {

    List<BorrowRecord> findByUserId(Integer userId);
}
