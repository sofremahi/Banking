package com.TIDDEV.mhn.banking.service.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import javax.persistence.*;

@Getter
@Setter
@Table(name = "TB_CUSTOMER_BANKING")
@Entity
public class Customer {
    @Id
    @Column(name = "CUSTOMER_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    @Column(name = "CUSTOMER_NAME" , nullable = false)
    private String name ;
    @Column(name = "PHONE", nullable = false ,unique = true)
    private String phone;
    @Column(name = "CUSTOMER_NUMBER", nullable = false ,unique = true)
    private String number;
    @Column(name = "NATIONAL_CODE" , nullable = false, unique = true)
    private String nationalCode;

}
