package com.rfigueroa.figscrm.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.rfigueroa.figscrm.entity.Contact;

public interface ContactRepository extends PagingAndSortingRepository<Contact, Integer> {
    
}
