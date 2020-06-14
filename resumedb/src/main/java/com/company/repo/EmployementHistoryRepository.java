package com.company.repo;

import com.company.entity.EmployementHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployementHistoryRepository extends JpaRepository<EmployementHistory,Integer> {
    List<EmployementHistory> findByUser_Id(Integer id);
    boolean existsEmployementHistoryById(Integer id);
}
