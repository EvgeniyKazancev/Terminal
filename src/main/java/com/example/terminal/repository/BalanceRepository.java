package com.example.terminal.repository;

import com.example.terminal.entity.Balance;
import com.example.terminal.entity.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BalanceRepository extends JpaRepository<Balance, Long> {

   Optional<Balance> findByUserId(Long userId);

}
