package com.rfigueroa.figscrm.dao;

import com.rfigueroa.figscrm.projections.CustomerDetailsProjection;
import com.rfigueroa.figscrm.projections.CustomerTableProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rfigueroa.figscrm.entity.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    // Tabular list
    Page<CustomerTableProjection> findAllProjectedBy(Pageable pageable);

    // Customer Details projection
    Optional<CustomerDetailsProjection> findCustomerById(Integer id);
    
}
