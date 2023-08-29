package com.example.Terminal.Services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class BalanceService extends JdbcTemplate {
   private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
   public   int getBalance() {


    }
}
