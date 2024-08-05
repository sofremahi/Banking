package com.TIDDEV.mhn.banking.service.Mapper;

import com.TIDDEV.mhn.banking.service.model.Transaction;
import com.TIDDEV.mhn.banking.service.modelDto.TransactionDto;

public interface TransactionMapper {
    Transaction dtoToTransaction(TransactionDto dto);
    TransactionDto transactionToDto(Transaction transaction);
}
