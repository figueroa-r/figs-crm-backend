package com.rfigueroa.figscrm.projections;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.rfigueroa.figscrm.entity.Ticket;
import com.rfigueroa.figscrm.entity.TicketInteraction;

@Projection(name = "ticketDetails", types = Ticket.class)
public interface TicketDetails {
    
    Integer getId();

    Boolean getIsOpen();

    @Value("#{target.getCategory().getId()}")
    Integer getCategoryId();

    @Value("#{target.getPriority().getId()}")
    Integer getPriorityId();

    @Value("#{target.getPrimaryContact().getId()}")
    Integer getPrimaryContactId();

    LocalDate getCreationDate();

    String getTicketNotes();

    List<TicketInteraction> getInteractions();
}
