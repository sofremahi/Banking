package com.TIDDEV.mhn.banking.service.serviceImpl;

import com.TIDDEV.mhn.banking.common.exception.NotFoundExceptionCustom;
import com.TIDDEV.mhn.banking.service.AccService;
import com.TIDDEV.mhn.banking.service.Mapper.AccountMapper;
import com.TIDDEV.mhn.banking.service.enums.AccountType;
import com.TIDDEV.mhn.banking.service.model.Account;
import com.TIDDEV.mhn.banking.service.model.Customer;
import com.TIDDEV.mhn.banking.service.modelDto.AccountDto;
import com.TIDDEV.mhn.banking.service.repository.AccRepository;
import com.TIDDEV.mhn.banking.service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@EnableScheduling
public class AccServiceImpl implements AccService {
    private final AccRepository accRepository;
    private final CustomerRepository customerRepository;

    private final AccountMapper accountMapper;
    @Override
    public List<AccountDto> findAll() {
        List<Account> accountList = accRepository.findAll();
       return accountList.stream().map(account -> accountMapper.accToDto(account)).collect(Collectors.toList());
    }

    @Override
    public Account findById(Long id) {
        return accRepository.findById(id).get();
    }

    @Override
    public Account findByNo(String no) {
        return accRepository.findByNo(no);
    }

    @Override
    public List<Account> findByDate(LocalDate date) {
        return accRepository.findByDate(date);
    }

    @Override
    public List<Account> findByType(AccountType type) {
        return accRepository.findByType(type);
    }

    @Override
    public List<Account> findActivationStatus(Boolean isActive) {
        return accRepository.findActivationStatus(isActive);
    }

    @Override
    @Transactional
    public List<AccountDto> addAccounts(Long customerId , List<AccountDto> accounts) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new NotFoundExceptionCustom("customer.not.found"));
List<Account> accountList = accounts.stream().map(acc -> accountMapper.dtoToAcc(acc)).collect(Collectors.toList());
        // Set the customer for each account
        accountList.forEach(account -> account.setCustomer(customer));
        // Save all accounts
        accRepository.saveAll(accountList);
        return accountList.stream().map(acc -> accountMapper.accToDto(acc)).collect(Collectors.toList());
    }

    @Scheduled(cron = "0 0 6 * * ?")
    @Transactional
    public void wage(){
        List<Account> accountList = accRepository.findAll();
        BigDecimal wage = new BigDecimal(600);
        accountList.forEach(account ->
        {if(account.getActiveStatus().equals(true) ) {
            if (account.getAccountAmount().compareTo(wage) >= 0) {
                account.setAccountAmount(account.getAccountAmount().subtract(wage));
                accRepository.save(account);
            } else {
                account.setAccountAmount(BigDecimal.ZERO);
                account.setActiveStatus(false);
                accRepository.save(account);
            }
        }
        });
        System.out.println("wage.payment.success");

    }
}
