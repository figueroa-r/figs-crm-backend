package com.rfigueroa.figscrm.projections;

import java.util.List;

import org.springframework.data.rest.core.config.Projection;

import com.rfigueroa.figscrm.entity.Contact;
import com.rfigueroa.figscrm.entity.ContactDetail;

@Projection(name = "contactDetails", types = Contact.class)
public interface ContactDetails {

    Integer getId();

    String getFirstName();

    String getLastName();

    String getTitle();

    String getDepartment();

    boolean getActive();

    Integer getAvatarId();

    List<ContactDetail> getContactsList();
    
}
