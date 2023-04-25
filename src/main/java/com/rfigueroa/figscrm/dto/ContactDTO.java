package com.rfigueroa.figscrm.dto;

import com.rfigueroa.figscrm.dto.ContactDetailDTO;

import java.util.List;

public class ContactDTO {

    private String firstName;

    private String lastName;

    private Boolean isActive;

    private Integer avatarId;

    private String title;

    private String department;

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
