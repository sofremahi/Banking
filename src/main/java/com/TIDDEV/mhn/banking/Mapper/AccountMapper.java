package com.TIDDEV.mhn.banking.Mapper;

import com.TIDDEV.mhn.banking.service.model.Account;
import com.TIDDEV.mhn.banking.service.modelDto.AccountDto;

public interface AccountMapper {
    Account dtoToAcc(AccountDto dto);
    AccountDto accToDto(Account account);

}
