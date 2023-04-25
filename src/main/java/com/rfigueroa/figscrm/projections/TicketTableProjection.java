package com.rfigueroa.figscrm.projections;

import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

public interface TicketTableProjection {

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

}
