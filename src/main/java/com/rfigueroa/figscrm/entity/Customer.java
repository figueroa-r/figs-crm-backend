package com.rfigueroa.figscrm.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}, name = "CUSTOMER_NAME_UNIQUE"))
@Data
@NoArgsConstructor
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @NotBlank(message = "Name is required")
    @Column(name = "name")
    private String name;

    @Column(name = "alias")
    private String alias;

    @Column(name = "company_type")
    private String companyType;

    @NotNull
    @Column(name = "is_active")
    private Boolean isActive;

    @NotNull
    @Column(name = "is_verified")
    private Boolean isVerified;

    @NotBlank(message = "Address is required")
    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @NotBlank(message = "City is required")
    @Column(name = "city")
    private String city;

    @NotBlank(message = "State is required")
    @Column(name = "state")
    private String state;

    @NotBlank(message = "Zip is required")
    @Column(name = "zip")
    private String zip;

    @JsonIgnore
    @OneToMany(mappedBy = "customer",
                cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
    private List<Contact> contacts;

    @JsonIgnore
    @OneToMany(mappedBy = "customer",
                cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
    private List<Ticket> tickets;



    // LOMBOK handles getters, setters, constructors, etc...

    // convenience methods for adding Contacts and Tickets

    public void addContact(Contact contactToAdd) {
        if (contacts == null) {
            contacts = new ArrayList<>();
        }

        contacts.add(contactToAdd);
        contactToAdd.setCustomer(this);
    }

    public void addTicket(Ticket ticketToAdd) {
        
        if (tickets == null) {
            tickets = new ArrayList<>();
        }

        tickets.add(ticketToAdd);
        ticketToAdd.setCustomer(this);
    }



}
