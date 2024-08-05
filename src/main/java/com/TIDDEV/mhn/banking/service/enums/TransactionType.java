package com.TIDDEV.mhn.banking.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionType {
    deposit((byte) 1 , "deposit") ,
    withdraw((byte) 2 , "withdraw"),
    transfer((byte) 3 , "transfer")
    ;
    private final byte code;
    private final String type ;
}
