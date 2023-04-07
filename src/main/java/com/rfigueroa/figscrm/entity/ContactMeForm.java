package com.rfigueroa.figscrm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contact_me_form")
@Data
@NoArgsConstructor
public class ContactMeForm {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;
    

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email_address")
    private String email;

    @Column(name = "phone_number")
    private String phone;

    @Column(name = "contact_note")
    private String contactNote;

    
}
