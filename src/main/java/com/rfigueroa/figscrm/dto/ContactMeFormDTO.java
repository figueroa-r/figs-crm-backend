package com.rfigueroa.figscrm.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ContactMeFormDTO {

    @Pattern(
            regexp = "^[a-zA-Z'-]+\\s[a-zA-Z'-]+$",
            message = "Please enter a valid first and last name")
    @NotBlank(message = "Full Name cannot be blank")
    private String fullName;

    @Email(message = "Please enter a valid email")
    @NotBlank(message = "Email cannot be blank")
    private String email;

    @Pattern(
            regexp = "^[+]?[0-9]{0,3}[-\\s.]?[(]?[0-9]{3}[)]?[-\\s.]?[0-9]{3}[-\\s.]?[0-9]{4,6}$",
            message = "Please enter a valid phone number"
    )
    @NotBlank(message = "Phone number cannot be blank")
    private String phone;

    @NotBlank(message = "Contact note cannot be blank")
    @Size(
            min = 10,
            max = 255,
            message = "please enter a number between {min} and {max}")
    private String contactNote;

    public ContactMeFormDTO(String fullName, String email, String phone, String contactNote) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.contactNote = contactNote;
    }

    public ContactMeFormDTO() {
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContactNote() {
        return contactNote;
    }

    public void setContactNote(String contactNote) {
        this.contactNote = contactNote;
    }
}
