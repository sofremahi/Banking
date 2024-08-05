package com.TIDDEV.mhn.banking.service.modelDto;

import com.TIDDEV.mhn.banking.service.enums.TransactionType;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class OperationTransferDto {
    private String nameMain;
    private String nameTarget;
    private String mainAccNo;
    private String targetAccNo;
    private TransactionType type;
    private BigDecimal transactionAmount;
    private BigDecimal remaining;
}
