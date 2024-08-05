package com.TIDDEV.mhn.banking.service.repository.RepositoryImpl;


import com.TIDDEV.mhn.banking.service.enums.AccountType;
import com.TIDDEV.mhn.banking.service.model.Account;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;

import static com.TIDDEV.mhn.banking.service.model.QAccount.account;
@Repository
public class AccRepositoryImpl implements AccCustomRepository {
    private JPAQueryFactory queryFactory;

    @Autowired
    public AccRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Account> findAll() {
        return queryFactory.selectFrom(account).fetch();
    }

    @Override
    public Account findByNo(String no) {
        return queryFactory.selectFrom(account).where(account.number.eq(no)).fetchOne();
    }

    @Override
    public List<Account> findByDate(LocalDate date) {
        return queryFactory.selectFrom(account).where(account.date.eq(date)).fetch();
    }

    @Override
    public List<Account> findByType(AccountType type) {
        return queryFactory.selectFrom(account).where(account.type.eq(type)).fetch();
    }

    @Override
    public List<Account> findActivationStatus(Boolean isActive) {
        return queryFactory.selectFrom(account).where(account.activeStatus.eq(isActive)).fetch();

    }

}


