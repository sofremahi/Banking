package com.TIDDEV.mhn.banking.service.Mapper;

import com.TIDDEV.mhn.banking.service.model.Account;
import com.TIDDEV.mhn.banking.service.modelDto.AccountDto;
import com.TIDDEV.mhn.banking.service.repository.AccRepository;
import com.TIDDEV.mhn.banking.service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.time.LocalDate;
import java.util.Random;
@Component
@RequiredArgsConstructor
public class AccountMapperImpl implements AccountMapper{
private final CustomerRepository customerRepository;
private final Random random = new Random();
private final AccRepository accRepository;
    @Override
    public Account dtoToAcc(AccountDto dto) {
       Account account = new Account();
       account.setAccountAmount(dto.getAccountAmount());
       account.setId(generateUniqueId());
       account.setNumber(dto.getNumber());
       account.setType(dto.getType());
       account.setActiveStatus(dto.getActiveStatus());
       account.setDate(LocalDate.now());
       return account;
    }

    @Override
    public AccountDto accToDto(Account account) {
        AccountDto dto = new AccountDto();
        dto.setAccountAmount(account.getAccountAmount());
        dto.setType(account.getType());
        dto.setNumber(account.getNumber());
        dto.setId(account.getId());
        dto.setActiveStatus(account.getActiveStatus());
        dto.setCustomerName(account.getCustomer().getName());
        return dto;
    }
    public Long generateUniqueId() {
        Long id;
        do {
            id = random.nextLong();
        } while (customerRepository.findById(id).isPresent()); // Check non repeat
        return id;
    }
}
