package com.TIDDEV.mhn.banking.service;


import com.TIDDEV.mhn.banking.service.enums.AccountType;
import com.TIDDEV.mhn.banking.service.model.Account;

import java.time.LocalDate;
import java.util.List;

public interface AccService   {
    List<Account> findAll() ;
    Account findById(Long id);
    Account findByNo(String no);
    List<Account> findByDate (LocalDate date);
    List<Account> findByType(AccountType type);
    List<Account> findActivationStatus(Boolean stat);

}
