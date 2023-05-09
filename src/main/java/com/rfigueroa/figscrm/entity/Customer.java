package com.rfigueroa.figscrm.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

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
    @Size(min = 3, max = 50, message = "Customer name must be between 3 and 50 characters long")
    @Pattern(
            regexp = "([a-zA-Z0-9]|[- @,.#&!'])*",
            message = "Name must be alphanumeric characters and the following special characters: '- , . # & !'"
    )
    @Column(name = "name")
    private String name;

    @Column(name = "alias")
    private String alias;

    @Column(name = "company_type")
    private String companyType;

    @NotNull(message = "isActive must have a true or false value")
    @Column(name = "is_active")
    private Boolean isActive;

    @NotNull(message = "isVerified must have a true or false value")
    @Column(name = "is_verified")
    private Boolean isVerified;

    @NotBlank(message = "Address 1 is required")
    @Size(min = 5, message = "Address must be at least 5 characters in length")
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
    @Pattern(
            regexp = "^(?!0{5})(\\d{5})(?!-?0{4})(-?\\d{4})?$",
            message = "Please enter a valid 5 or 9 digit zip code")
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
