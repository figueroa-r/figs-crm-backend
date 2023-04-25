package com.rfigueroa.figscrm.projections;

import org.springframework.beans.factory.annotation.Value;

public interface ContactTableProjection {
    Integer getId();

    @Value("#{(target.getFirstName() + \" \" + target.getLastName).trim()}")
    String getFullName();

    String getTitle();

    String getDepartment();

    Boolean getIsActive();

    Integer getAvatarId();
}
