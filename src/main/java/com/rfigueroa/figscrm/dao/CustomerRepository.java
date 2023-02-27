package com.rfigueroa.figscrm.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.rfigueroa.figscrm.entity.Customer;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Integer>{
    
}
