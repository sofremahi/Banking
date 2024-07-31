package com.TIDDEV.mhn.banking.service.repository.RepositoryImpl;

import com.TIDDEV.mhn.banking.service.enums.TransactionType;
import com.TIDDEV.mhn.banking.service.model.Transaction;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

import static com.TIDDEV.mhn.banking.service.model.QTransaction.transaction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public class TransactionRepositoryImpl implements TransactionCustomRepository {
    private  JPAQueryFactory queryFactory ;
    @Autowired
    public TransactionRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Transaction findByNo(String no) {
        return queryFactory.selectFrom(transaction).where(transaction.trackingNo.eq(no)).fetchOne();
    }

    @Override
    public List<Transaction> findByDate(LocalDate date) {
        return queryFactory.selectFrom(transaction).where(transaction.dateTime.eq(date)).fetch();
    }

    @Override
    public List<Transaction> findByType(TransactionType type) {
        return queryFactory.selectFrom(transaction).where(transaction.type.eq(type)).fetch();
    }

    @Override
    public List<Transaction> findByToAcc(Long id) {
        return queryFactory.selectFrom(transaction).where(transaction.toAccId.eq(id)).fetch();
    }

    @Override
    public List<Transaction> findByAccTo(Long id) {
        return queryFactory.selectFrom(transaction).where(transaction.account.id.eq(id)).fetch();
    }



}
