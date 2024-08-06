package com.TIDDEV.mhn.banking.web;

import com.TIDDEV.mhn.banking.common.response.Response;
import com.TIDDEV.mhn.banking.service.CustomerService;
import com.TIDDEV.mhn.banking.service.model.Customer;
import com.TIDDEV.mhn.banking.service.modelDto.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/rest/banking/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;

    private final MessageSource messageSource;

    public String getMessage(String code) {
        return messageSource.getMessage(code, null, Locale.getDefault());
    }

    @GetMapping("/find")
    public Response<List<Customer>> find() {
        return new Response<>(service.findAllCustom(), HttpStatus.FOUND);
    }
    @GetMapping("/find/by/id/{customerId}")
    public Response<Customer> findById(@PathVariable("customerId") Long id) {
        return new Response<>(service.findById(id), HttpStatus.FOUND);
    }

    @GetMapping("/find/by/name/{customerName}")
    public Response<List<Customer>> findByName(@PathVariable("customerName") String name) {
        return new Response<>(service.findByName(name), HttpStatus.FOUND);
    }

    @GetMapping("/find/by/phone/{customerPhone}")
    public Response<Customer> findByPhone(@PathVariable("customerPhone") String phone) {
        return new Response<>(service.findByPhone(phone), HttpStatus.FOUND);
    }
    @GetMapping("/find/by/no/{customerNumber}")
    public Response<Customer> findByNumber(@PathVariable("customerNumber") String number) {
        return new Response<>(service.findByNumber(number), HttpStatus.FOUND);
    }
    @GetMapping("/find/by/national/{customerNationalCode}")
    public Response<Customer> findByNationalCode(@PathVariable("customerNationalCode") String nationalCode) {
        return new Response<>(service.findByNationalCode(nationalCode), HttpStatus.FOUND);
    }
@PostMapping
    public Response<String> addCustomer(@RequestBody CustomerDto dto){
         service.addCustomer(dto);
         return new Response<>(getMessage("customer.added")) ;

}


}
