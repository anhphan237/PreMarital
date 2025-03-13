package com.example.premarital.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "transactions")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "wallet_id", nullable = false)
    private Wallet wallet;

    private Long amount;
    private String transactionType;
    private Date transactionTime;
    private String transactionStatus;
    private Long balanceBefore;
    private Long transactionFee;
    private Long totalAmount;
    @Column(name = "is_active")
    private Boolean isActive;

    @OneToOne(mappedBy = "transaction")
    private WithdrawRequest withdrawRequest;

    @OneToOne(mappedBy = "transaction")
    private ConsultationBooking consultationBooking;
}
