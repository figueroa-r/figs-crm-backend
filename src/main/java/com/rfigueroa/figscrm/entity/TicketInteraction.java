package com.rfigueroa.figscrm.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "interaction")
@Data
@NoArgsConstructor
public class TicketInteraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotBlank(message = "A valid user must be provided")
    @Column(name = "user")
    private String user;

    @NotNull(message = "A valid interaction date must be provided")
    @Column(name = "interaction_date")
    private Date interactionDate;

    @NotBlank(message = "Interaction details cannot be blank")
    @Length(min = 10, max = 255, message = "Interaction details must be between 10 and 255 characters")
    @Column(name = "interaction_details")
    private String interactionDetails;

    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    // lombok handles getters, setters, and constructors
    
}
