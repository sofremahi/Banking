package com.TIDDEV.mhn.banking.service.serviceImpl;

import com.TIDDEV.mhn.banking.service.Mapper.TransactionMapper;
import com.TIDDEV.mhn.banking.service.TransactionService;
import com.TIDDEV.mhn.banking.service.enums.TransactionStatus;
import com.TIDDEV.mhn.banking.service.enums.TransactionType;
import com.TIDDEV.mhn.banking.service.model.Transaction;
import com.TIDDEV.mhn.banking.service.modelDto.TransactionDto;
import com.TIDDEV.mhn.banking.service.repository.AccRepository;
import com.TIDDEV.mhn.banking.service.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
private final AccRepository accRepository;
private final TransactionMapper transactionMapper;
@PersistenceContext
private EntityManager entityManager;

    @Override
    public List<TransactionDto> findAll() {
        List<Transaction> transactions = transactionRepository.findDeletedStatusFalse();
        return transactions.stream().map(transaction ->transactionMapper.transactionToDto(transaction)).
                collect(Collectors.toList());
    }
    @Override
    public Transaction findById(UUID id) {
        return transactionRepository.findById(id).get();
    }

    @Override
    public Transaction findByNo(String no) {
        return transactionRepository.findByNo(no);
    }

    @Override
    public List<Transaction> findByDate(LocalDate date) {
        return transactionRepository.findByDate(date);
    }

    @Override
    public List<Transaction> findByType(TransactionType type) {
        return transactionRepository.findByType(type);
    }

    @Override
    public List<Transaction> findByToAcc(Long id) {
        return transactionRepository.findByToAcc(id);
    }

    @Override
    public List<Transaction> findByAccTo(Long id) {
        return transactionRepository.findByAccTo(id);
    }

    @Override
    public void add(TransactionType type, Long toAcc, BigDecimal amount, Long accId , TransactionStatus status) {
        String sequenceQuery = "SELECT tracking_no_seq.NEXTVAL FROM DUAL";
        Long sequenceValue = ((Number) entityManager.createNativeQuery(sequenceQuery).getSingleResult()).longValue();
        Random random  = new Random();
        Transaction transaction = new Transaction();
        transaction.setTrackingNo("TRX0" + random.nextInt(10)+ "0" + sequenceValue );
        transaction.setAccount(accRepository.findById(accId).get());
        transaction.setTransactionAmount(amount);
        transaction.setType(type);
        transaction.setDateTime(LocalDate.now());
        transaction.setToAccId(toAcc);
        transaction.setIsDeleted(false);
        transaction.setStatus(status);
        transactionRepository.save(transaction);
        log.info("transaction with UUID " + transaction.getId() + " added");
    }
}
