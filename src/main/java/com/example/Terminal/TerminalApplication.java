package com.example.Terminal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@SpringBootApplication
public class TerminalApplication {
	public JdbcTemplate jdbcTemplate(DataSource Terminal){
    return new JdbcTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(TerminalApplication.class, args);
	}

}
