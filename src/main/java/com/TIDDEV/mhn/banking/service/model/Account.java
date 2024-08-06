package com.TIDDEV.mhn.banking.service.model;

import com.TIDDEV.mhn.banking.service.enums.AccountType;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import javax.persistence.*;

@Getter
@Setter
@Table(name = "TB_ACCOUNT_BANKING")
@Entity
public class Account {
    @Id
    @Column(name = "ACCOUNT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "ACCOUNT_NUMBER" , nullable = false)
    private String number;
    @Column(name = "ACCOUNT_DATE" , nullable = false)
    private LocalDate date ;
    @Enumerated(EnumType.ORDINAL)
    @Column(name = "ACCOUNT_TYPE" , nullable = false)
    private AccountType type;
    @Column(name = "ACCOUNT_AMOUNT")
    private BigDecimal accountAmount;
    @Column(name = "ACTIVE_STATUS" , nullable = false)
    private Boolean activeStatus;
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID", nullable = false)
    @JsonBackReference
    private Customer customer;
}
