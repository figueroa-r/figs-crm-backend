package com.rfigueroa.figscrm.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.rfigueroa.figscrm.entity.ContactDetail;

public interface ContactDetailRepository extends PagingAndSortingRepository<ContactDetail, Integer> {
    
}
