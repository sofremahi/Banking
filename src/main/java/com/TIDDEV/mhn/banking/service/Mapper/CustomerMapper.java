package com.TIDDEV.mhn.banking.service.Mapper;

import com.TIDDEV.mhn.banking.service.model.Customer;
import com.TIDDEV.mhn.banking.service.modelDto.CustomerDto;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


public interface CustomerMapper {
    Customer dtoToCustomer(CustomerDto dto);
    CustomerDto customerToDto(Customer customer);
}
