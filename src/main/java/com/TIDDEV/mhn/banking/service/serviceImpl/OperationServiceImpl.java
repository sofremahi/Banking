package com.TIDDEV.mhn.banking.service.serviceImpl;

import com.TIDDEV.mhn.banking.common.exception.NotEnoughMoneyException;
import com.TIDDEV.mhn.banking.common.exception.NotValidInputException;
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
import net.bytebuddy.asm.Advice;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Locale;

@Service
@Slf4j
@RequiredArgsConstructor
public class OperationServiceImpl implements OperationService {
    private final AccRepository accRepository;

    private final CustomerRepository customerRepository;
    private final TransactionRepository transactionRepository;

    private final MessageSource messageSource;

    public String getMessage(String code) {
        return messageSource.getMessage(code, null, Locale.getDefault());
    }
    public OperationTransferDto transferDto(String accNoMain, String accNoTarget,
                                            BigDecimal transactionAmount) {
        // check if the statements given are valid
        if (accRepository.findByNo(accNoMain) != null &&
                accRepository.findByNo(accNoTarget) != null &&
                transactionAmount.compareTo(new BigDecimal(0)) >= 0) {
            //operating
            Account main = accRepository.findByNo(accNoMain);
            Account target = accRepository.findByNo(accNoTarget);
            if (main.getAccountAmount().subtract(transactionAmount).compareTo(new BigDecimal("10")) <= 0) {
                throw new NotEnoughMoneyException(getMessage("insufficient.funds"));
            } else {
                main.setAccountAmount(main.getAccountAmount().subtract(transactionAmount));
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
        }else if(accRepository.findByNo(accNoMain) == null){
            throw new NotValidInputException(getMessage("try.valid.account.number"));
        }else if (accRepository.findByNo(accNoTarget) == null){
            throw new NotValidInputException(getMessage("try.valid.target.account.number"));
        }else if( transactionAmount.compareTo(new BigDecimal(0)) < 0){
            throw new NotValidInputException(getMessage("try entering a valid transaction amount"));
        }

        throw new NotValidInputException(getMessage("try.valid.input"));
    }

    @Override
    public OperatingDepositDto depositDto(String accNoMain, BigDecimal transactionAmount) {
        if (accRepository.findByNo(accNoMain) != null &&
                transactionAmount.compareTo(new BigDecimal(0)) >= 0) {
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
        } else if (accRepository.findByNo(accNoMain) == null) {
            throw new NotValidInputException(getMessage("try.valid.account.number"));
        }else if( transactionAmount.compareTo(new BigDecimal(0)) < 0){
            throw new NotValidInputException(getMessage("try.valid.transaction.amount") );
        }
        throw new NotValidInputException(getMessage("try.valid.input.account.transaction"));
    }

    @Override
    public OperationWithdrawDto withdrawDto(String accNoMain, BigDecimal transactionAmount) {
        if (accRepository.findByNo(accNoMain) != null &&
                transactionAmount.compareTo(new BigDecimal(0)) >= 0) {
            //operating
            Account main = accRepository.findByNo(accNoMain);
            if (main.getAccountAmount().subtract(transactionAmount).compareTo(new BigDecimal("10")) <= 0) {
                throw new NotEnoughMoneyException(getMessage("insufficient.funds"));
            } else {
                main.setAccountAmount(main.getAccountAmount().subtract(transactionAmount));
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
        }else if(accRepository.findByNo(accNoMain) == null){
            throw new NotValidInputException(getMessage("try.valid.account.number")) ;
        }else if(transactionAmount.compareTo(new BigDecimal(0)) < 0){
            throw new NotValidInputException(getMessage("try.valid.transaction.amount")) ;
        }
        throw new NotValidInputException(getMessage("try.valid.input.account.transaction"));
    }
@Transactional
    @Override
    public void deleteTransactions() {
        transactionRepository.setDeletedStatusTrue();
    }
}
