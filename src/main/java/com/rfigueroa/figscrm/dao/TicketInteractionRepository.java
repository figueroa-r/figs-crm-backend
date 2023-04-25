package com.rfigueroa.figscrm.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rfigueroa.figscrm.entity.TicketInteraction;

public interface TicketInteractionRepository extends JpaRepository<TicketInteraction, Integer> {
    
}
