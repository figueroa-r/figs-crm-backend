package com.rfigueroa.figscrm.controller;

import com.rfigueroa.figscrm.dto.InteractionDTO;
import com.rfigueroa.figscrm.dto.RestPageResponseDTO;
import com.rfigueroa.figscrm.dto.TicketContextDTO;
import com.rfigueroa.figscrm.dto.TicketDTO;
import com.rfigueroa.figscrm.projections.InteractionProjection;
import com.rfigueroa.figscrm.projections.TicketDetailProjection;
import com.rfigueroa.figscrm.projections.TicketTableProjection;
import com.rfigueroa.figscrm.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "https://master.d2b1tg1ojgscyw.amplifyapp.com"})
@RequestMapping("/api/v2/tickets")
public class TicketController {

    TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) { this.ticketService = ticketService; }

    @GetMapping("/context")
    public ResponseEntity<TicketContextDTO> getTicketContext(@RequestParam(required = true) Integer customerId) {

        return new ResponseEntity<TicketContextDTO>(ticketService.getTicketContext(customerId), HttpStatus.OK);
    }

    // get pageable list of tickets
    @GetMapping
    public ResponseEntity<RestPageResponseDTO<TicketTableProjection>> getTicketsPage(
            @RequestParam(required = true) Integer customerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "creationDate, asc") String[] sort) {

        return new ResponseEntity<>(ticketService.getTicketsByPageRequest(page, size, sort, customerId), HttpStatus.OK);
    }

    // get single ticket
    @GetMapping("/{ticketId}")
    public ResponseEntity<TicketDetailProjection> getSingleTicket(@PathVariable Integer ticketId) {

        return new ResponseEntity<TicketDetailProjection>(ticketService.getTicketWithDetails(ticketId), HttpStatus.OK);
    }

    // create a ticket
    @PostMapping
    public ResponseEntity<TicketDetailProjection> createNewTicket(@Valid @RequestBody TicketDTO ticketDTO) {

        return new ResponseEntity<TicketDetailProjection>(ticketService.createNewTicket(ticketDTO), HttpStatus.CREATED);
    }

    // create an interaction
    @PostMapping("/ticket-interactions")
    public ResponseEntity<InteractionProjection> createNewInteraction(@Valid @RequestBody InteractionDTO interactionDTO) {

        return new ResponseEntity<InteractionProjection>(
                ticketService.createNewInteraction(interactionDTO),
                HttpStatus.CREATED);
    }

    // modify ticket fields (patch?)
    @PatchMapping("/{ticketId}")
    public ResponseEntity<TicketDetailProjection> updateExistingTicket(
            @RequestBody TicketDTO ticketDTO,
            @PathVariable Integer ticketId) {

        return new ResponseEntity<TicketDetailProjection>(
                ticketService.updateExistingTicket(ticketDTO, ticketId),
                HttpStatus.OK);
    }

    // delete a ticket
    @DeleteMapping("/{ticketId}")
    public HttpStatus deleteTicket(@PathVariable Integer ticketId) {

        ticketService.deleteTicketById(ticketId);
        return HttpStatus.OK;
    }

    // delete an interaction
    @DeleteMapping("/ticket-interactions/{interactionId}")
    public HttpStatus deleteTicketInteraction(@PathVariable Integer interactionId) {

        ticketService.deleteInteractionById(interactionId);
        return HttpStatus.OK;
    }
}
