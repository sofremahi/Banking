package com.TIDDEV.mhn.banking.service;

import com.TIDDEV.mhn.banking.service.enums.TransactionStatus;
import com.TIDDEV.mhn.banking.service.enums.TransactionType;
import com.TIDDEV.mhn.banking.service.model.Account;
import com.TIDDEV.mhn.banking.service.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TransactionService {
    List<Transaction> findAll() ;
    Transaction findById(UUID id);
    Transaction findByNo(String no);
    List<Transaction> findByDate(LocalDate date);
    List<Transaction> findByType (TransactionType type);
    List<Transaction> findByToAcc(Long id);
    List<Transaction> findByAccTo(Long id);
    void add(TransactionType type , Long toAcc, BigDecimal amount , Long accId , TransactionStatus status);



}
