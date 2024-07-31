package com.TIDDEV.mhn.banking.service.serviceImpl;

import com.TIDDEV.mhn.banking.service.AccService;
import com.TIDDEV.mhn.banking.service.enums.AccountType;
import com.TIDDEV.mhn.banking.service.model.Account;
import com.TIDDEV.mhn.banking.service.repository.AccRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class AccServiceImpl implements AccService {
    private AccRepository accRepository;

    @Autowired
    public void setAccRepository(AccRepository accRepository) {
        this.accRepository = accRepository;
    }

    @Override
    public List<Account> findAll() {
        return accRepository.findAll();
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
}
