package com.TIDDEV.mhn.banking.service.repository.RepositoryImpl;

import com.TIDDEV.mhn.banking.service.enums.TransactionType;
import com.TIDDEV.mhn.banking.service.model.Account;
import com.TIDDEV.mhn.banking.service.model.Transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TransactionCustomRepository {
    List<Transaction> findDeletedStatusFalse();

    Transaction findByNo(String no);

    List<Transaction> findByDate(LocalDate date);

    List<Transaction> findByType(TransactionType type);

    List<Transaction> findByToAcc(Long id);

    List<Transaction> findByAccTo(Long id);

    void setDeletedStatusTrue();
}
