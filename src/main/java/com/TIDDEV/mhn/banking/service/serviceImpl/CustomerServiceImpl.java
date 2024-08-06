package com.TIDDEV.mhn.banking.service.serviceImpl;

import com.TIDDEV.mhn.banking.service.CustomerService;
import com.TIDDEV.mhn.banking.service.Mapper.CustomerMapper;
import com.TIDDEV.mhn.banking.service.model.Customer;
import com.TIDDEV.mhn.banking.service.modelDto.CustomerDto;
import com.TIDDEV.mhn.banking.service.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
private final CustomerMapper customerMapper;


    @Override
    public List<Customer> findAllCustom() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).get();
    }

    @Override
    public List<Customer> findByName(String name) {
        return  customerRepository.findByName(name);
    }

    @Override
    public Customer findByPhone(String phone) {
       return customerRepository.findByPhone(phone);
    }

    @Override
    public Customer findByNumber(String number) {
        return customerRepository.findByNumber(number);
    }

    @Override
    public Customer findByNationalCode(String nationalCode) {
        return customerRepository.findByNationalCode(nationalCode);
    }
    @Override
    public void addCustomer(CustomerDto dto) {
        Customer customer = customerMapper.dtoToCustomer(dto) ;
       customerRepository.save(customer);

    }

}
