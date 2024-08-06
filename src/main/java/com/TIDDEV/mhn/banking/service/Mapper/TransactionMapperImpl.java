package com.TIDDEV.mhn.banking.service.Mapper;
import com.TIDDEV.mhn.banking.service.model.Transaction;
import com.TIDDEV.mhn.banking.service.modelDto.TransactionDto;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;
@Component
public class TransactionMapperImpl implements TransactionMapper {
    @Override
    public Transaction dtoToTransaction(TransactionDto dto) {
        Transaction transaction = new Transaction();
        transaction.setStatus(dto.getStatus());
        transaction.setTransactionAmount(dto.getTransactionAmount());
        transaction.setType(dto.getType());
        transaction.setId(UUID.randomUUID());
        transaction.setToAccId(dto.getToAccId());
        transaction.setDateTime(LocalDate.now());
        transaction.setTrackingNo(dto.getTrackingNo());
        return transaction;
    }

    @Override
    public TransactionDto transactionToDto(Transaction transaction) {
        TransactionDto dto = new TransactionDto();
        dto.setTransactionAmount(transaction.getTransactionAmount());
        dto.setStatus(transaction.getStatus());
        dto.setAccNo(transaction.getAccount().getNumber());
        dto.setId(transaction.getId());
        dto.setType(transaction.getType());
        dto.setDateTime(transaction.getDateTime());
        dto.setTrackingNo(transaction.getTrackingNo());
        dto.setToAccId(transaction.getToAccId());
        return dto;
    }
}
