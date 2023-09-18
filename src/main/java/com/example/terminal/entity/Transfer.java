package com.example.terminal.entity;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "Transfer")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @OneToOne(optional = false,orphanRemoval = true)
    @JoinColumn(name = "sender_operation_id",nullable = false)
    private Operation senderOperation;

    @OneToOne(optional = false,orphanRemoval = true)
    @JoinColumn(name = "recepient_operation_id",nullable = false)
    private Operation recipientOperation;

}
