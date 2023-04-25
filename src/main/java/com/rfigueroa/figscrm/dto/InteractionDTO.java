package com.rfigueroa.figscrm.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class InteractionDTO {

    @NotBlank(message = "A valid user must be provided")
    private String user;
    @NotNull(message = "A valid interaction date must be provided")
    private Date interactionDate;
    @NotBlank(message = "Interaction details cannot be blank")
    @Length(min = 10, max = 255, message = "Interaction details must be between 10 and 255 characters")
    private String interactionDetails;
    @NotNull
    @Min(value = 1, message = "Please provide a valid Ticket Id")
    private Integer ticketId;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getInteractionDate() {
        return interactionDate;
    }

    public void setInteractionDate(Date interactionDate) {
        this.interactionDate = interactionDate;
    }

    public String getInteractionDetails() {
        return interactionDetails;
    }

    public void setInteractionDetails(String interactionDetails) {
        this.interactionDetails = interactionDetails;
    }

    public Integer getTicketId() {
        return ticketId;
    }

    public void setTicketId(Integer ticketId) {
        this.ticketId = ticketId;
    }

    public InteractionDTO() {
    }

    public InteractionDTO(String user, Date interactionDate, String interactionDetails, Integer ticketId) {
        this.user = user;
        this.interactionDate = interactionDate;
        this.interactionDetails = interactionDetails;
        this.ticketId = ticketId;
    }
}
