package com.rfigueroa.figscrm.projections;

import com.rfigueroa.figscrm.entity.TicketInteraction;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.util.List;

public interface TicketDetailProjection {

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

    List<InteractionProjection> getInteractions();
}
