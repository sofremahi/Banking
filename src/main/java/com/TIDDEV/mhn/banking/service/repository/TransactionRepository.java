package com.TIDDEV.mhn.banking.service.repository;
import com.TIDDEV.mhn.banking.service.model.Transaction;
import com.TIDDEV.mhn.banking.service.repository.RepositoryImpl.TransactionCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface TransactionRepository
extends JpaRepository<Transaction, UUID> , TransactionCustomRepository
{

}
