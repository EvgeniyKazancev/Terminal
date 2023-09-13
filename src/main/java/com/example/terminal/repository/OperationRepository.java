package com.example.terminal.repository;

import com.example.terminal.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;
@Repository
public interface OperationRepository extends JpaRepository<Operation,Long> {
   List<Operation>  findOperationByDateBetween (Long userId, ZonedDateTime startDate, ZonedDateTime finishDate);
}
