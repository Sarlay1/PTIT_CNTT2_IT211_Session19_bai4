package com.example.session19_bai4.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.Id;


@Entity
@Table(name = "tokens")

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

@Builder

public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String tokenValue;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType;

    private boolean revoked;

    private boolean expired;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;


}