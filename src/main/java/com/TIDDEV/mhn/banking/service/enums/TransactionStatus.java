package com.TIDDEV.mhn.banking.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TransactionStatus {
    success((byte) 1 , "successful") ,
    failure((byte) 2 , "failed")
    ;
    private final byte code;
    private final String type ;
}
