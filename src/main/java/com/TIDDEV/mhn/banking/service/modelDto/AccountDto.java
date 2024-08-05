package com.TIDDEV.mhn.banking.service.modelDto;
import com.TIDDEV.mhn.banking.service.enums.AccountType;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
@Getter
@Setter
public class AccountDto {
    private String CustomerName;
    private Long id;
    private String number;
    private AccountType type;
    private BigDecimal accountAmount;
    private Boolean activeStatus;

}
