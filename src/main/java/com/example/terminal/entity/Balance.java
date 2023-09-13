package com.example.terminal.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Balance")
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;

    @Column(name = "balance")
    private Long balance;

    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;
}
