package com.TIDDEV.mhn.banking.web;

import com.TIDDEV.mhn.banking.common.exception.NotEnoughMoneyException;
import com.TIDDEV.mhn.banking.common.exception.NotValidInputException;
import com.TIDDEV.mhn.banking.common.response.Response;
import com.TIDDEV.mhn.banking.service.AccService;
import com.TIDDEV.mhn.banking.service.OperationService;
import com.TIDDEV.mhn.banking.service.TransactionService;
import com.TIDDEV.mhn.banking.service.enums.TransactionStatus;
import com.TIDDEV.mhn.banking.service.enums.TransactionType;
import com.TIDDEV.mhn.banking.service.modelDto.OperatingDepositDto;
import com.TIDDEV.mhn.banking.service.modelDto.OperationTransferDto;
import com.TIDDEV.mhn.banking.service.modelDto.OperationWithdrawDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Locale;

@RestController
@RequestMapping("rest/banking/operate")
@RequiredArgsConstructor
public class OperationsController {
    private final OperationService service;
    private final TransactionService transactionService;
    private final AccService accService;
    private final MessageSource messageSource;

    public String getMessage(String code) {
        return messageSource.getMessage(code, null, Locale.getDefault());
    }

    @GetMapping("/transfer/{mainAccNo}/{targetAccNo}/{transactionAmount}")
    public Response<OperationTransferDto> transfer(@PathVariable String mainAccNo,
                                                   @PathVariable String targetAccNo,
                                                   @PathVariable BigDecimal transactionAmount) {
        OperationTransferDto dto;
        try {
            dto = service.transferDto(mainAccNo, targetAccNo, transactionAmount);
            transactionService.add(TransactionType.transfer,
                    accService.findByNo(targetAccNo).getId(), transactionAmount,
                    accService.findByNo(mainAccNo).getId(), TransactionStatus.success);
            return new Response<>(dto);
        } catch (NotEnoughMoneyException e) {
            transactionService.add(TransactionType.transfer,
                    accService.findByNo(targetAccNo).getId(), transactionAmount,
                    accService.findByNo(mainAccNo).getId(), TransactionStatus.failure);
            throw e;
        } catch (NotValidInputException e){
            throw e ;
        }

    }

    @GetMapping("/deposit/{mainAccNo}/{transactionAmount}")
    public Response<OperatingDepositDto> deposit(@PathVariable String mainAccNo,
                                                 @PathVariable BigDecimal transactionAmount) {
        OperatingDepositDto dto ;
        try{
            dto =service.depositDto(mainAccNo, transactionAmount) ;
         transactionService.add(TransactionType.deposit,
                null, transactionAmount,
                accService.findByNo(mainAccNo).getId(), TransactionStatus.success);
        return new Response<>(dto);}catch (NotEnoughMoneyException e){
            transactionService.add(TransactionType.deposit,
                    null, transactionAmount,
                    accService.findByNo(mainAccNo).getId(), TransactionStatus.failure);
            throw e;
        }catch (NotValidInputException e){
            throw e ;
        }
    }

    @GetMapping("/withdraw/{mainAccNo}/{transactionAmount}")
    public Response<OperationWithdrawDto> withdraw(@PathVariable String mainAccNo,
                                                   @PathVariable BigDecimal transactionAmount) {
        OperationWithdrawDto dto ;
        try{ dto = service.withdrawDto(mainAccNo, transactionAmount);
        transactionService.add(TransactionType.withdraw,
                null, transactionAmount,
                accService.findByNo(mainAccNo).getId(), TransactionStatus.success);
        return new Response<>(dto);}catch (NotEnoughMoneyException e){
            transactionService.add(TransactionType.withdraw,
                    null, transactionAmount,
                    accService.findByNo(mainAccNo).getId(), TransactionStatus.failure);
            throw e;
        }catch (NotValidInputException e){
            throw e ;
        }
    }
    @GetMapping("/delete/transactions")
    public void deleteTransactions(){
        service.deleteTransactions();
        System.out.println(getMessage("all.transactions.status.set.to.deleted"));
    }
}
