package com.example.terminal.entity;

import com.example.terminal.enums.OperationType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


import java.time.ZonedDateTime;


@Getter
@Setter
@Entity
@Table(name = "Operation")
public class Operation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id",nullable = false)
    private Users user;

    @Enumerated(EnumType.STRING)
    @Column(name = "operation_Type")
    private OperationType operationType;

    @Column(name = "summa")
    private Long summa;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private ZonedDateTime date;


}
