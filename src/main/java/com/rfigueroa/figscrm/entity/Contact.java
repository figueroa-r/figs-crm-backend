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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contact")
@Data   
@NoArgsConstructor
public class Contact {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name="title", length = 50)
    private String title;

    @Column(name = "department", length = 50)
    private String department;

    @Column(name = "active")
    private boolean active;

    @Column(name = "avatar_id")
    private Integer avatarId;
    
    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(
        mappedBy = "contact",
        fetch = FetchType.LAZY,
        cascade = CascadeType.ALL)
    private List<ContactDetail> contactsList;
    

    // convenience method for contact details
    public void addContactDetail(ContactDetail contactDetailToAdd) {

        if (contactsList == null) {
            contactsList = new ArrayList<>();
        }

        contactsList.add(contactDetailToAdd);
        contactDetailToAdd.setContact(this);
    }


}

