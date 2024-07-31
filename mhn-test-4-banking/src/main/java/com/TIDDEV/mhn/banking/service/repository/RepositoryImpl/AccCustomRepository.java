package com.TIDDEV.mhn.banking.service.repository.RepositoryImpl;

import com.TIDDEV.mhn.banking.service.enums.AccountType;
import com.TIDDEV.mhn.banking.service.model.Account;

import java.time.LocalDate;
import java.util.List;

public interface AccCustomRepository {
    List<Account> findAll() ;

    Account findByNo(String no);
    List<Account> findByDate (LocalDate date);
    List<Account> findByType(AccountType type);
    List<Account> findActivationStatus(Boolean isActive);

}
