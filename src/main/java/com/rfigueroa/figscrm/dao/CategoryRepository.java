package com.rfigueroa.figscrm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rfigueroa.figscrm.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
}
