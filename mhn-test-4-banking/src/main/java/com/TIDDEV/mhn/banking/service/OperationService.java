package com.TIDDEV.mhn.banking.service;

import com.TIDDEV.mhn.banking.service.modelDto.OperatingDepositDto;
import com.TIDDEV.mhn.banking.service.modelDto.OperationTransferDto;
import com.TIDDEV.mhn.banking.service.modelDto.OperationWithdrawDto;

import java.math.BigDecimal;

public interface OperationService {
    OperationTransferDto transferDto(String accNoMain, String accNoTarget,
                                     BigDecimal transactionAmount);

    OperatingDepositDto depositDto(String accNoMain, BigDecimal transactionAmount);
OperationWithdrawDto withdrawDto(String accNoMain , BigDecimal transactionAmount);
}
