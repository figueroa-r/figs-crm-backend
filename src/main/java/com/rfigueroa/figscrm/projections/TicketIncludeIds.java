package com.rfigueroa.figscrm.projections;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.rfigueroa.figscrm.entity.Ticket;

@Projection(name = "ticketIncludeIds", types = { Ticket.class })
public interface TicketIncludeIds {
    
    Boolean getIsOpen();

    @Value("#{target.getCategory().getId()}")
    Integer getCategoryId();

    @Value("#{target.getPriority().getId()}")
    Integer getPriorityId();

    @Value("#{target.getPrimaryContact().getId()}")
    Integer getPrimaryContactId();

    LocalDate getCreationDate();

    String getTicketNotes();

    


}
