package com.rfigueroa.figscrm.entity;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ticket")
@Data
@NoArgsConstructor
public class Ticket {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "is_open")
    private Boolean isOpen;

    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "priority_id")
    private Priority priority;

    @ManyToOne(
        optional = true,
        cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH}
        )
    @JoinColumn(name = "primary_contact_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Contact primaryContact;

    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Column(name = "creation_date")
    private LocalDate creationDate;

    // may change this to instead use a text column instead of varchar... depends on ticket notes size
    @Column(name = "ticket_notes", length = 500)
    private String ticketNotes;

    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "customer_id")
    private Customer customer;


    @OneToMany(
        mappedBy = "ticket",
        fetch = FetchType.LAZY,
        cascade= CascadeType.ALL)
    private List<TicketInteraction> interactions;


    // convenience method for interactions
    public void addInteraction(TicketInteraction interactionToAdd) {
        if (interactions == null) {
            interactions = new ArrayList<>();
        }

        interactions.add(interactionToAdd);
        interactionToAdd.setTicket(this);
    }
}

