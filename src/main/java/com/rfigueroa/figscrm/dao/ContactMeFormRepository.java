package com.rfigueroa.figscrm.dao;

import org.springframework.data.repository.CrudRepository;

import com.rfigueroa.figscrm.entity.ContactMeForm;

public interface ContactMeFormRepository extends CrudRepository<ContactMeForm, Integer> {
    
}
