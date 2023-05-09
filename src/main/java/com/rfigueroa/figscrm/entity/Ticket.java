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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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

    @NotNull
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
        fetch = FetchType.LAZY,
        cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH}
        )
    @JoinColumn(name = "primary_contact_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Contact primaryContact;

//    @DateTimeFormat(pattern = "MM-dd-yyyy")
    @Column(name = "creation_date")
    @PastOrPresent(message = "Creation date cannot be a future date")
    private LocalDate creationDate;

    // may change this to instead use a text column instead of varchar... depends on ticket notes size
    @Column(name = "ticket_notes", length = 500)
    @Size(min = 10, max = 500, message = "Please provide a description between {min} and {max} characters")
    @NotBlank(message = "Ticket description cannot be blank")
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

