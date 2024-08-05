package com.TIDDEV.mhn.banking.web;

import ch.qos.logback.core.util.Loader;
import com.TIDDEV.mhn.banking.common.response.Response;
import com.TIDDEV.mhn.banking.service.TransactionService;
import com.TIDDEV.mhn.banking.service.enums.TransactionType;
import com.TIDDEV.mhn.banking.service.model.Account;
import com.TIDDEV.mhn.banking.service.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/banking/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService service;

    @GetMapping("/find")
    public Response<List<Transaction>> find() {
        return new Response<>(service.findAll(), HttpStatus.FOUND);
    }

    @GetMapping("/find/by/id/{id}")
    public Response<Transaction> findById(@PathVariable("id") UUID id) {
        return new Response<>(service.findById(id), HttpStatus.FOUND);
    }
    @GetMapping("/find/by/no/{no}")
    public Response<Transaction> findByNo(@PathVariable("no") String no) {
        return new Response<>(service.findByNo(no), HttpStatus.FOUND);
    }
    @GetMapping("/find/by/date/{date}")
    public Response<List<Transaction>> findByDate(@PathVariable("date")
                                                      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                      LocalDate date) {
        return new Response<>(service.findByDate(date), HttpStatus.FOUND);
    }
    @GetMapping("/find/by/type/{type}")
    public Response<List<Transaction>> findByType(@PathVariable("type") TransactionType type) {
        return new Response<>(service.findByType(type), HttpStatus.FOUND);
    }
    @GetMapping("/find/acc/to/{id}")
    public Response<List<Transaction>> findByAccTo(@PathVariable("id")  Long id) {
        return new Response<>(service.findByAccTo(id), HttpStatus.FOUND);
    }
    @GetMapping("/find/to/acc/{id}")
    public Response<List<Transaction>> findByToAcc(@PathVariable("id")  Long id) {
        return new Response<>(service.findByToAcc(id), HttpStatus.FOUND);
    }
}
