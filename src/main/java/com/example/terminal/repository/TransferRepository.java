package com.example.terminal.repository;

import com.example.terminal.entity.Balance;
import com.example.terminal.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransferRepository extends JpaRepository<Transfer,Long> {

}
