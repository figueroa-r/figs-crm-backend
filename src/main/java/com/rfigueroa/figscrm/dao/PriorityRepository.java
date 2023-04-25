package com.rfigueroa.figscrm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rfigueroa.figscrm.entity.Priority;

public interface PriorityRepository extends JpaRepository<Priority, Integer> {
    
}
