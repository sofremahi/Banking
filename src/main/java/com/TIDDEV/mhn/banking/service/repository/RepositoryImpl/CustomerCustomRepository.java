package com.TIDDEV.mhn.banking.service.repository.RepositoryImpl;


import com.TIDDEV.mhn.banking.service.model.Customer;

import java.util.List;

public interface CustomerCustomRepository  {

    List<Customer> findByName(String name);

    Customer findByPhone(String phone);

    Customer findByNumber(String number);

    Customer findByNationalCode(String nationalCode);
}
