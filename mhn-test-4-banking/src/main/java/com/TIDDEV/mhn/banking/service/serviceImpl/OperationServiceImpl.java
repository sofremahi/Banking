package com.TIDDEV.mhn.banking.service.serviceImpl;

import com.TIDDEV.mhn.banking.common.exception.NotEnoughMoneyException;
import com.TIDDEV.mhn.banking.service.OperationService;
import com.TIDDEV.mhn.banking.service.enums.TransactionType;
import com.TIDDEV.mhn.banking.service.model.Account;
import com.TIDDEV.mhn.banking.service.modelDto.OperatingDepositDto;
import com.TIDDEV.mhn.banking.service.modelDto.OperationTransferDto;
import com.TIDDEV.mhn.banking.service.modelDto.OperationWithdrawDto;
import com.TIDDEV.mhn.banking.service.repository.AccRepository;
import com.TIDDEV.mhn.banking.service.repository.CustomerRepository;
import com.TIDDEV.mhn.banking.service.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService{
    private final AccRepository accRepository;

    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    public OperationTransferDto transferDto(String accNoMain ,String accNoTarget,
                                            BigDecimal transactionAmount){
        //operating
        Account main = accRepository.findByNo(accNoMain);
        Account target = accRepository.findByNo(accNoTarget);
        main.setAccountAmount(main.getAccountAmount().subtract(transactionAmount));
        if (main.getAccountAmount().compareTo(new BigDecimal("10")) <= 0) {
            throw new NotEnoughMoneyException("Insufficient funds in the main account.");
        }
        target.setAccountAmount(target.getAccountAmount().add(transactionAmount));
            accRepository.save(main);
            accRepository.save(target);

        //setting the dto
        OperationTransferDto dto = new OperationTransferDto();
       dto.setMainAccNo(accNoMain);
       dto.setTargetAccNo(accNoTarget);
       dto.setNameMain(main.getCustomer().getName());
       dto.setNameTarget(target.getCustomer().getName());
       dto.setType(TransactionType.transfer);
       dto.setTransactionAmount(transactionAmount);
       dto.setRemaining(main.getAccountAmount());
       return dto;
    }

    @Override
    public OperatingDepositDto depositDto(String accNoMain, BigDecimal transactionAmount) {

        //operating
        Account main = accRepository.findByNo(accNoMain);
        main.setAccountAmount(main.getAccountAmount().add(transactionAmount));
        accRepository.save(main);
        //setting dto
        OperatingDepositDto dto = new OperatingDepositDto();
        dto.setMainAccNo(accNoMain);
        dto.setNameMain(main.getCustomer().getName());
        dto.setType(TransactionType.deposit);
        dto.setTransactionAmount(transactionAmount);
        dto.setRemaining(main.getAccountAmount());
        return dto;
    }

    @Override
    public OperationWithdrawDto withdrawDto(String accNoMain, BigDecimal transactionAmount) {
        //operating
        Account main = accRepository.findByNo(accNoMain);
        main.setAccountAmount(main.getAccountAmount().subtract(transactionAmount));
        if (main.getAccountAmount().compareTo(new BigDecimal("10")) <= 0) {
            throw new NotEnoughMoneyException("Insufficient funds in the main account.");
        }
        accRepository.save(main);
        //setting dto
        OperationWithdrawDto dto = new OperationWithdrawDto();
        dto.setMainAccNo(accNoMain);
        dto.setNameMain(main.getCustomer().getName());
        dto.setType(TransactionType.withdraw);
        dto.setTransactionAmount(transactionAmount);
        dto.setRemaining(main.getAccountAmount());
        return dto;
    }
}
