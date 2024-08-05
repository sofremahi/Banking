package com.TIDDEV.mhn.banking.service.repository.RepositoryImpl;
import com.TIDDEV.mhn.banking.service.model.Customer;
import com.TIDDEV.mhn.banking.service.repository.CustomerRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import static com.TIDDEV.mhn.banking.service.model.QCustomer.customer;
@Repository
public class CustomerRepositoryImpl implements CustomerCustomRepository {

    private final JPAQueryFactory queryFactory ;
    @Autowired
    public CustomerRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Customer> findByName(String name) {
        return queryFactory.selectFrom(customer).where(customer.name.eq(name)).fetch();
    }

    @Override
    public Customer findByPhone(String phone) {
        return queryFactory.selectFrom(customer).where(customer.phone.eq(phone)).fetchOne();
    }

    @Override
    public Customer findByNumber(String number) {
        return queryFactory.selectFrom(customer).where(customer.number.eq(number)).fetchOne();
    }
    @Override
    public Customer findByNationalCode(String nationalCode) {
        return queryFactory.selectFrom(customer).where(customer.nationalCode.eq(nationalCode)).fetchOne();
    }
}
