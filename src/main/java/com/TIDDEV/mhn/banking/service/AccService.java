package com.TIDDEV.mhn.banking.service;


import com.TIDDEV.mhn.banking.service.enums.AccountType;
import com.TIDDEV.mhn.banking.service.model.Account;
import com.TIDDEV.mhn.banking.service.modelDto.AccountDto;

import java.time.LocalDate;
import java.util.List;

public interface AccService   {
    List<AccountDto> addAccounts(Long customerId ,List<AccountDto> accounts);
    List<AccountDto> findAll() ;
    Account findById(Long id);
    Account findByNo(String no);
    List<Account> findByDate (LocalDate date);
    List<Account> findByType(AccountType type);
    List<Account> findActivationStatus(Boolean stat);

}
