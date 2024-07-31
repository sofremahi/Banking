package com.TIDDEV.mhn.banking.service.repository;

import com.TIDDEV.mhn.banking.service.enums.AccountType;
import com.TIDDEV.mhn.banking.service.model.Account;


import com.TIDDEV.mhn.banking.service.repository.RepositoryImpl.AccCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccRepository
extends JpaRepository<Account , Long > , AccCustomRepository
{


}
