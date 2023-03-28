package com.rfigueroa.figscrm.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;

import com.rfigueroa.figscrm.entity.Contact;

public interface ContactRepository extends PagingAndSortingRepository<Contact, Integer> {
    

    // additional method to search contacts by customerId
    @RestResource(path = "byCustomerId", rel = "byCustomerId" )
    Page<Contact> findByCustomerId(@Param("customerId") Integer customerId, Pageable pageable );
}
