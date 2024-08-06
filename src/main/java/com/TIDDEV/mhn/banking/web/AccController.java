package com.TIDDEV.mhn.banking.web;
import com.TIDDEV.mhn.banking.common.response.Response;
import com.TIDDEV.mhn.banking.service.AccService;
import com.TIDDEV.mhn.banking.service.enums.AccountType;
import com.TIDDEV.mhn.banking.service.model.Account;
import com.TIDDEV.mhn.banking.service.modelDto.AccountDto;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/rest/banking/account")
@RequiredArgsConstructor
public class AccController {
    private final AccService service;
    @GetMapping("/find")
    public Response<List<AccountDto>> find() {
        return new Response<>(service.findAll(), HttpStatus.FOUND);
    }
    @GetMapping("/find/by/id/{id}")
    public Response<Account> findById(@PathVariable("id") Long id){
    return new Response<>(service.findById(id) , HttpStatus.FOUND);
    }

    @GetMapping("/find/by/no/{no}")
    public Response<Account> findByNo(@PathVariable("no") String no){
        return new Response<>(service.findByNo(no) , HttpStatus.FOUND);
    }
    @GetMapping("/find/by/date/{date}")
    public Response<List<Account>> findByDate(@PathVariable("date")
                                                  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                  LocalDate date){
        return new Response<>(service.findByDate(date) , HttpStatus.FOUND);
    }
    @GetMapping("/find/by/type/{type}")
    public Response<List<Account>> findByType(@PathVariable("type") AccountType type){
        return new Response<>(service.findByType(type) , HttpStatus.FOUND);
    }   @GetMapping("/find/by/status/{status}")
    public Response<List<Account>> findByStat(@PathVariable("status") Boolean stat){
        return new Response<>(service.findActivationStatus(stat) , HttpStatus.FOUND);
    }
    @PostMapping("/add/accounts/to/{customerId}")
    public Response<List<AccountDto>> addAccounts(@PathVariable("customerId") Long id ,
                                               @RequestBody List<AccountDto> accounts) {
         service.addAccounts(id ,accounts);
        return new Response<>("accounts added ");
    }
}
