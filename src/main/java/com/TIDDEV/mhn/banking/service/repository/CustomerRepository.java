package com.TIDDEV.mhn.banking.service.repository;

import com.TIDDEV.mhn.banking.service.model.Customer;

import com.TIDDEV.mhn.banking.service.repository.RepositoryImpl.CustomerCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;



public interface CustomerRepository
extends JpaRepository<Customer , Long> , CustomerCustomRepository
{


}
