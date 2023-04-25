package com.rfigueroa.figscrm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rfigueroa.figscrm.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    
}
