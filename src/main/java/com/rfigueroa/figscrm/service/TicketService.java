package com.rfigueroa.figscrm.service;

import com.rfigueroa.figscrm.dao.*;
import com.rfigueroa.figscrm.dto.InteractionDTO;
import com.rfigueroa.figscrm.dto.PageDTO;
import com.rfigueroa.figscrm.dto.RestPageResponseDTO;
import com.rfigueroa.figscrm.dto.TicketDTO;
import com.rfigueroa.figscrm.entity.*;
import com.rfigueroa.figscrm.exception.EntityNotFoundException;
import com.rfigueroa.figscrm.projections.InteractionProjection;
import com.rfigueroa.figscrm.projections.TicketDetailProjection;
import com.rfigueroa.figscrm.projections.TicketTableProjection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private TicketRepository ticketRepository;
    private TicketInteractionRepository ticketInteractionRepository;
    private CustomerRepository customerRepository;
    private CategoryRepository categoryRepository;
    private PriorityRepository priorityRepository;
    private ContactRepository contactRepository;
    private ProjectionFactory projectionFactory;

    @Autowired
    public TicketService(TicketRepository ticketRepository,
                         TicketInteractionRepository ticketInteractionRepository,
                         CustomerRepository customerRepository,
                         CategoryRepository categoryRepository,
                         PriorityRepository priorityRepository,
                         ContactRepository contactRepository,
                         ProjectionFactory projectionFactory) {
        this.ticketRepository = ticketRepository;
        this.ticketInteractionRepository = ticketInteractionRepository;
        this.customerRepository = customerRepository;
        this.categoryRepository = categoryRepository;
        this.priorityRepository = priorityRepository;
        this.contactRepository = contactRepository;
        this.projectionFactory = projectionFactory;
    }


    public RestPageResponseDTO<TicketTableProjection> getTicketsByPageRequest(int page, int size, String[] sort, Integer customerId) {

        // set sorting parameters
        List<Sort.Order> orders = new ArrayList<>();
        orders.add(new Sort.Order(Sort.Direction.fromString(sort[1]), sort[0]));

        // create pageable input for repository
        Pageable pagingSort = PageRequest.of(page, size, Sort.by(orders));

        // get pageable from repo
        Page<TicketTableProjection> ticketPage = ticketRepository.findAllByCustomerId(customerId, pagingSort);

        // list of tickets
        List<TicketTableProjection> ticketsList = ticketPage.getContent();

        if(ticketsList.isEmpty() && page != 0) {
            // message is for non-zero based page index
            throw new EntityNotFoundException("No Tickets found for the current page, try a page less than " + page);
        }

        // structure page data for response
        PageDTO pageState = new PageDTO(
                ticketPage.getNumber(),
                size,
                ticketPage.getTotalElements(),
                ticketPage.getTotalPages());

        return new RestPageResponseDTO<>(ticketsList, pageState);
    }

    @Transactional
    public TicketDetailProjection getTicketWithDetails(Integer ticketId) {

        Optional<TicketDetailProjection> ticketOptional = ticketRepository.findTicketById(ticketId);

        // get the ticket as all as the associated entities...
        TicketDetailProjection ticket = ticketOptional.get();
        ticket.getInteractions();

        return ticket;
    }

    @Transactional
    public TicketDetailProjection createNewTicket(TicketDTO newTicket) {

        // get reference to associated entities by provided ids...
        Customer customerReference = customerRepository.getReferenceById(newTicket.getCustomerId());
        Category categoryReference = categoryRepository.getReferenceById(newTicket.getCategoryId());
        Priority priorityReference = priorityRepository.getReferenceById(newTicket.getPriorityId());
        // TODO change to find and ensure that the contact is one that belongs to the customer...
        Contact contactReference = contactRepository.getReferenceById(newTicket.getPrimaryContactId());

        // create ticket entity and associate entities...
        Ticket persistTicket = new Ticket();

        persistTicket.setIsOpen(newTicket.getIsOpen());
        persistTicket.setCategory(categoryReference);
        persistTicket.setPriority(priorityReference);
        persistTicket.setPrimaryContact(contactReference);
        persistTicket.setCreationDate(newTicket.getCreationDate());
        persistTicket.setTicketNotes(newTicket.getTicketNotes());
        persistTicket.setCustomer(customerReference);

        Ticket createdTicket = ticketRepository.save(persistTicket);

        return projectionFactory.createProjection(TicketDetailProjection.class, createdTicket);
    }

    @Transactional
    public InteractionProjection createNewInteraction(InteractionDTO interactionDTO) {

        // get reference to ticket...
        Ticket ticketReference = ticketRepository.getReferenceById(interactionDTO.getTicketId());

        // create interaction and map values from DTO
        TicketInteraction persistInteraction = new TicketInteraction();

        persistInteraction.setUser(interactionDTO.getUser());
        persistInteraction.setInteractionDate(interactionDTO.getInteractionDate());
        persistInteraction.setInteractionDetails(interactionDTO.getInteractionDetails());

        // create association
        ticketReference.addInteraction(persistInteraction);

        // save interaction
        TicketInteraction createdInteraction = ticketInteractionRepository.save(persistInteraction);


        return projectionFactory.createProjection(InteractionProjection.class, createdInteraction);

    }

    @Transactional
    public TicketDetailProjection updateExistingTicket(TicketDTO ticketDTO, Integer ticketId) {

        // get reference to existing ticket
        Ticket ticketReference = ticketRepository.getReferenceById(ticketId);

        // update values that are non-null
        if(ticketDTO.getIsOpen() != null) ticketReference.setIsOpen(ticketDTO.getIsOpen());
        if(ticketDTO.getCreationDate() != null) ticketReference.setCreationDate(ticketDTO.getCreationDate());
        if(ticketDTO.getTicketNotes() != null) ticketReference.setTicketNotes(ticketDTO.getTicketNotes());

        // get and update associated values
        if(ticketDTO.getCategoryId() != null) {
            ticketReference.setCategory(
                    categoryRepository.getReferenceById(ticketDTO.getCategoryId())
            );
        }

        if(ticketDTO.getPriorityId() != null) {
            ticketReference.setPriority(
                    priorityRepository.getReferenceById(ticketDTO.getPriorityId())
            );
        }

        if(ticketDTO.getPrimaryContactId() != null) {
            ticketReference.setPrimaryContact(
                    contactRepository.getReferenceById(ticketDTO.getPrimaryContactId())
            );
        }

        // save our updated entity
        ticketRepository.save(ticketReference);

        // get and return our updated ticket as a projection
        return getTicketWithDetails(ticketId);
    }

    public void deleteTicketById(Integer ticketId) {

        ticketRepository.deleteById(ticketId);
    }


    public void deleteInteractionById(Integer interactionId) {

        ticketInteractionRepository.deleteById(interactionId);
    }
}
