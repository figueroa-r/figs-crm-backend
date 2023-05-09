package com.rfigueroa.figscrm.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(
        name = "contact_me_form",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"email_address", "phone_number"},
                name = "EMAIL_PHONE_UNIQUE"))
@Data
@NoArgsConstructor
public class ContactMeForm {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer Id;

    @Pattern(
            regexp = "^[A-Z][a-z]+(?: [A-Z][a-z]*[.]?)+$",
            message = "Please enter a valid first and last name")
    @NotBlank(message = "Full Name cannot be blank")
    @Column(name = "full_name")
    private String fullName;

    @Email(message = "Please enter a valid email")
    @NotBlank(message = "Email cannot be blank")
    @Column(name = "email_address")
    private String email;

    @Pattern(
            regexp = "^[+]?[0-9]{0,3}[-\\s.]?[(]?[0-9]{3}[)]?[-\\s.]?[0-9]{3}[-\\s.]?[0-9]{4,6}$",
            message = "Please enter a valid phone number"
    )
    @NotBlank(message = "Phone number cannot be blank")
    @Column(name = "phone_number")
    private String phone;

    @NotBlank(message = "Contact note cannot be blank")
    @Size(
            min = 10,
            max = 255,
            message = "please enter a number between {min} and {max}")
    @Column(name = "contact_note")
    private String contactNote;

    
}
