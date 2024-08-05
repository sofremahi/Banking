package com.TIDDEV.mhn.banking.Mapper;

import com.TIDDEV.mhn.banking.service.model.Customer;
import com.TIDDEV.mhn.banking.service.modelDto.CustomerDto;

public interface CustomerMapper {
    Customer dtoToCustomer(CustomerDto dto);
    CustomerDto customerToDto(Customer customer);
}
