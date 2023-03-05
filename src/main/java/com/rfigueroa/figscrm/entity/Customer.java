package com.rfigueroa.figscrm.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "customer")
@Data
@NoArgsConstructor
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "name")
    private String name;

    @Column(name = "alias")
    private String alias;

    @Column(name = "company_type")
    private String companyType;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_verified")
    private Boolean isVerified;

    @Column(name = "address1")
    private String address1;

    @Column(name = "address2")
    private String address2;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip")
    private String zip;

    @OneToMany(mappedBy = "customer",
                cascade = CascadeType.ALL,
                fetch = FetchType.LAZY)
    private List<Contact> contacts;

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
