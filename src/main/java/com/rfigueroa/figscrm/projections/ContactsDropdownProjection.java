package com.rfigueroa.figscrm.projections;

import org.springframework.beans.factory.annotation.Value;

public interface ContactsDropdownProjection {
    
    Integer getId();
    
    @Value("#{(target.getFirstName() + \" \" + target.getLastName).trim()}")
    String getFullName();

    Integer getAvatarId();
}
