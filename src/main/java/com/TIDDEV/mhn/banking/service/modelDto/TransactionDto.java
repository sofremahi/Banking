package com.TIDDEV.mhn.banking.service.modelDto;

import com.TIDDEV.mhn.banking.service.enums.TransactionStatus;
import com.TIDDEV.mhn.banking.service.enums.TransactionType;
import com.TIDDEV.mhn.banking.service.model.Account;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class TransactionDto {
    private UUID id;
    private String trackingNo;
    private LocalDate dateTime;
    private TransactionType type;
    private Long toAccId;
    private BigDecimal transactionAmount;
    private TransactionStatus status;
    private Account account;
}
