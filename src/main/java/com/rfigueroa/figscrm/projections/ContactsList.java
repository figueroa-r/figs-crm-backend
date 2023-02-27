package com.rfigueroa.figscrm.projections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.rest.core.config.Projection;

import com.rfigueroa.figscrm.entity.Contact;

@Projection( name = "contactsAvatarList", types = Contact.class )
public interface ContactsList {
    
    Integer getId();
    
    @Value("#{target.getFirstName() + \" \" + target.getLastName}")
    String getFullName();

    Integer getAvatarId();
}
