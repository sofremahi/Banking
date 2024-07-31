package com.TIDDEV.mhn.banking.service.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AccountType {
    type1((byte) 1 , "current") ,
    type2((byte) 2 ,"long term"),
    type3((byte) 3 ,"short term"),
    type4((byte) 4 ,"loan"),
    ;
    private final byte code;
    private  final String type;


}
