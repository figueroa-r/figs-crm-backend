package com.rfigueroa.figscrm.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.rfigueroa.figscrm.entity.Ticket;


public interface TicketRespository extends PagingAndSortingRepository<Ticket, Integer>{
    
}
