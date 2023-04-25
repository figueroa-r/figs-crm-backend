package com.rfigueroa.figscrm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rfigueroa.figscrm.entity.ContactDetail;

public interface ContactDetailRepository extends JpaRepository<ContactDetail, Integer> {
    
}
