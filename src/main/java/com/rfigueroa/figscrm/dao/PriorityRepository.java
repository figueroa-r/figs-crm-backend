package com.rfigueroa.figscrm.dao;

import org.springframework.data.repository.CrudRepository;

import com.rfigueroa.figscrm.entity.Priority;

public interface PriorityRepository extends CrudRepository<Priority, Integer> {
    
}
