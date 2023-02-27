package com.rfigueroa.figscrm.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.rfigueroa.figscrm.entity.TicketInteraction;

public interface TicketInteractionRepository extends PagingAndSortingRepository<TicketInteraction, Integer> {
    
}
