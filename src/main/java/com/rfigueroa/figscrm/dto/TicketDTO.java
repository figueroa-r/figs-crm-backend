package com.rfigueroa.figscrm.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;


public class TicketDTO {

    @NotNull(message = "isOpen must have a true or false value")
    private Boolean isOpen;
    @NotNull(message = "Category must have a valid Id")
    @Min(value = 1, message = "Category must have  valid Id")
    private Integer categoryId;
    @NotNull(message = "Priority must have a valid Id")
    @Min(value = 1, message = "Priority must have a valid Id")
    private Integer priorityId;
    @NotNull(message = "Primary Contact must have a valid Id")
    @Min(value = 1, message = "Primary Contact must have a valid Id")
    private Integer primaryContactId;
    @PastOrPresent(message = "Creation date cannot be a future date")
    private LocalDate creationDate;
    @NotBlank(message = "Ticket Notes cannot be blank")
    private String ticketNotes;
    @NotNull(message = "Customer must have a valid Id")
    @Min(value = 1, message = "Customer must have a valid Id")
    private Integer customerId;

    public TicketDTO() {
    }

    public TicketDTO(Boolean isOpen,
                     Integer categoryId,
                     Integer priorityId,
                     Integer primaryContactId,
                     LocalDate creationDate,
                     String ticketNotes,
                     Integer customerId) {
        this.isOpen = isOpen;
        this.categoryId = categoryId;
        this.priorityId = priorityId;
        this.primaryContactId = primaryContactId;
        this.creationDate = creationDate;
        this.ticketNotes = ticketNotes;
        this.customerId = customerId;
    }

    public Boolean getIsOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean open) {
        isOpen = open;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Integer priorityId) {
        this.priorityId = priorityId;
    }

    public Integer getPrimaryContactId() {
        return primaryContactId;
    }

    public void setPrimaryContactId(Integer primaryContactId) {
        this.primaryContactId = primaryContactId;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public String getTicketNotes() {
        return ticketNotes;
    }

    public void setTicketNotes(String ticketNotes) {
        this.ticketNotes = ticketNotes;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
}
