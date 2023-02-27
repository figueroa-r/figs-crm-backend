package com.rfigueroa.figscrm.dao;

import org.springframework.data.repository.CrudRepository;

import com.rfigueroa.figscrm.entity.Category;

public interface CategoryRepository extends CrudRepository<Category, Integer> {
    
}
