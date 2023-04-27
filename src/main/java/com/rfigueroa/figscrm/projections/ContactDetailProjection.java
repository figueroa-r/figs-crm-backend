package com.rfigueroa.figscrm.projections;

import java.util.List;

public interface ContactDetailProjection {

    Integer getId();

    String getFirstName();

    String getLastName();

    String getTitle();

    String getDepartment();

    Boolean getIsActive();

    Integer getAvatarId();

    List<ContactDetailEntityProjection> getContactsList();
    
}
