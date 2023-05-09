package com.rfigueroa.figscrm.dto;

import com.rfigueroa.figscrm.entity.Category;
import com.rfigueroa.figscrm.entity.Priority;
import com.rfigueroa.figscrm.projections.ContactTableProjection;
import com.rfigueroa.figscrm.projections.ContactsDropdownProjection;

import java.util.List;

public class TicketContextDTO {

    private List<ContactsDropdownProjection> contacts;

    private List<Priority> priorities;

    private List<Category> categories;

    public TicketContextDTO() {
    }

    public TicketContextDTO(List<ContactsDropdownProjection> contacts,
                            List<Priority> priorities,
                            List<Category> categories) {
        this.contacts = contacts;
        this.priorities = priorities;
        this.categories = categories;
    }

    public List<ContactsDropdownProjection> getContacts() {
        return contacts;
    }

    public void setContacts(List<ContactsDropdownProjection> contacts) {
        this.contacts = contacts;
    }

    public List<Priority> getPriorities() {
        return priorities;
    }

    public void setPriorities(List<Priority> priorities) {
        this.priorities = priorities;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
