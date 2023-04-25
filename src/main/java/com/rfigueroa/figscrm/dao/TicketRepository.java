package com.rfigueroa.figscrm.dao;

import com.rfigueroa.figscrm.projections.TicketDetailProjection;
import com.rfigueroa.figscrm.projections.TicketTableProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.rfigueroa.figscrm.entity.Ticket;

import java.util.Optional;


public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    
    // additional method to search by customerId
//    @RestResource(path = "byCustomerId", rel = "byCustomerId" )
//    Page<Ticket> findByCustomerId(@Param("customerId") Integer customerId, Pageable pageable);

    // refined method using projection
    Page<TicketTableProjection> findAllByCustomerId(Integer customerId, Pageable pageable);

    Optional<TicketDetailProjection> findTicketById(Integer ticketId);
}
