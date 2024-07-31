package com.TIDDEV.mhn.banking.web;

import com.TIDDEV.mhn.banking.common.response.Response;
import com.TIDDEV.mhn.banking.service.AccService;
import com.TIDDEV.mhn.banking.service.OperationService;
import com.TIDDEV.mhn.banking.service.TransactionService;
import com.TIDDEV.mhn.banking.service.enums.TransactionType;
import com.TIDDEV.mhn.banking.service.modelDto.OperatingDepositDto;
import com.TIDDEV.mhn.banking.service.modelDto.OperationTransferDto;
import com.TIDDEV.mhn.banking.service.modelDto.OperationWithdrawDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("rest/banking/operate")
@RequiredArgsConstructor
public class OperationsController {
    private final OperationService service;
    private final TransactionService transactionService;
    private final AccService accService;

    @GetMapping("/transfer/{mainAccNo}/{targetAccNo}/{transactionAmount}")
    public Response<OperationTransferDto> transfer(@PathVariable String mainAccNo,
                                                   @PathVariable String targetAccNo,
                                                   @PathVariable BigDecimal transactionAmount){
        transactionService.add(TransactionType.transfer,
                accService.findByNo(targetAccNo).getId(),transactionAmount,
                accService.findByNo(mainAccNo).getId());
        return new Response<>(service.transferDto(mainAccNo,targetAccNo,transactionAmount) , HttpStatus.OK);
    }
    @GetMapping("/deposit/{mainAccNo}/{transactionAmount}")
    public Response<OperatingDepositDto> deposit(@PathVariable String mainAccNo,
                                                  @PathVariable BigDecimal transactionAmount){
        transactionService.add(TransactionType.deposit,
                null,transactionAmount,
                accService.findByNo(mainAccNo).getId());
        return new Response<>(service.depositDto(mainAccNo,transactionAmount) , HttpStatus.OK);
    }
    @GetMapping("/withdraw/{mainAccNo}/{transactionAmount}")
    public Response<OperationWithdrawDto> withdraw(@PathVariable String mainAccNo,
                                                   @PathVariable BigDecimal transactionAmount){
        transactionService.add(TransactionType.withdraw,
                null,transactionAmount,
                accService.findByNo(mainAccNo).getId());
        return new Response<>(service.withdrawDto(mainAccNo,transactionAmount) , HttpStatus.OK);
    }
}
