package com.rfigueroa.figscrm.projections;

public interface CustomerTableProjection {

    Integer getId();

    String getAvatarUrl();

    String getName();

    String getCompanyType();

    Boolean getIsActive();

    Boolean getIsVerified();

}
