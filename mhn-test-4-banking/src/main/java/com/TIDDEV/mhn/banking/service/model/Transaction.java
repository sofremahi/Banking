package com.TIDDEV.mhn.banking.service.model;

import com.TIDDEV.mhn.banking.service.enums.TransactionType;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@Table(name = "TB_TRANSACTION_BANKING")
@Entity
@ToString
public class Transaction {
    @Id
    @Column(name = "TRANSACTION_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(name = "TRACKING_NO" , nullable = false , unique = true)
    private String trackingNo;
    @Column(name = "DATE_TIME")
    private LocalDate dateTime;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "TRANSACTION_TYPE" , nullable = false)
    private TransactionType type;
    @Column(name = "TO_ACCOUNT_ID")
    private Long toAccId;
    @Column(name = "TRANSACTION_AMOUNT")
    private BigDecimal transactionAmount;
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    @JsonBackReference
    private Account account;
}
