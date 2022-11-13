package com.example.logAnalyzer.repository;

import com.example.logAnalyzer.entity.LogData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LogRepository extends JpaRepository<LogData,Integer> {

    List<LogData> findByLogType(String level);

    List<LogData> findAllByCreatedTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

    List<LogData> findByMessageContaining(String keyword);
}
