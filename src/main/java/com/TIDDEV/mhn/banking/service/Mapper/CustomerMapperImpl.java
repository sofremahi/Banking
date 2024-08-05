package com.TIDDEV.mhn.banking.service.Mapper;

import com.TIDDEV.mhn.banking.service.model.Customer;
import com.TIDDEV.mhn.banking.service.modelDto.CustomerDto;
import com.TIDDEV.mhn.banking.service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;

import java.util.Random;

@RequiredArgsConstructor
public class CustomerMapperImpl implements CustomerMapper{
    private final Random random = new Random();
private final CustomerRepository customerRepository;
    @Override
    public Customer dtoToCustomer(CustomerDto dto) {
     Customer customer = new Customer();
     customer.setId(generateUniqueId());
     customer.setName(dto.getName());
    customer.setPhone(dto.getPhone());
    customer.setNationalCode(dto.getNationalCode());
    return customer ;
    }

    @Override
    public CustomerDto customerToDto(Customer customer) {
        CustomerDto dto = new CustomerDto();
        dto.setId(customer.getId());
        dto.setName(customer.getName());
        dto.setPhone(customer.getPhone());
        dto.setNationalCode(customer.getNationalCode());
        return dto;
    }
    public Long generateUniqueId() {
        Long id;
        do {
            id = random.nextLong();
        } while (customerRepository.findById(id).isPresent()); // Check non repeat
        return id;
    }
}
