package com.example.terminal.repository;

import com.example.terminal.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users,Long> {

void deleteUsersById (Long userId);
}
