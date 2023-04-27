package com.rfigueroa.figscrm.dto;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.*;
import java.util.List;

public class ContactDTO {

    @NotBlank(message = "First Name is required")
    @Pattern(regexp = "^[a-zA-z'-]+$", message = "Please enter a valid first name")
    @Size(max = 50, message = "Please enter a first name shorter than 50 characters")
    private String firstName;

    @Pattern(regexp = "^[a-zA-z'-]+$", message = "Please enter a valid last name")
    @Size(max = 50, message = "Please enter a last name shorter than 50 characters")
    private String lastName;

    @NotNull(message = "Is Active field must be true or false")
    private Boolean isActive;

    @Range(min = 1, max = 24, message = "Value must be between {min} and {max}")
    private Integer avatarId;

    @NotBlank(message = "Title is required")
    private String title;

    private String department;

    @NotNull(message = "A Customer id must be provided")
    private Integer customerId;

    private List<ContactDetailDTO> contactMethods;

    // constructors
    public ContactDTO() {
    }

    public ContactDTO(String firstName, String lastName, Boolean isActive, Integer avatarId, String title, String department, Integer customerId, List<ContactDetailDTO> contactMethods) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.avatarId = avatarId;
        this.title = title;
        this.department = department;
        this.customerId = customerId;
        this.contactMethods = contactMethods;
    }

    // getters and setters

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Integer getAvatarId() {
        return avatarId;
    }

    public void setAvatarId(Integer avatarId) {
        this.avatarId = avatarId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<ContactDetailDTO> getContactMethods() {
        return contactMethods;
    }

    public void setContactMethods(List<ContactDetailDTO> contactMethods) {
        this.contactMethods = contactMethods;
    }
}
