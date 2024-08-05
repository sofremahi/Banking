package com.TIDDEV.mhn.banking.service;

import com.TIDDEV.mhn.banking.service.model.Customer;

import java.time.LocalDate;
import java.util.List;

public interface CustomerService {
    List<Customer> findAllCustom();
    Customer findById(Long id);
    List<Customer> findByName(String name);
    Customer findByPhone(String phone);
    Customer findByNumber(String number);
    Customer findByNationalCode(String nationalCode);

}
