package com.rfigueroa.figscrm.projections;

public interface CustomerDetailsProjection {

    Integer getId();

    String getAvatarUrl();

    String getName();

    String getAlias();

    String getCompanyType();

    Boolean getIsActive();

    Boolean getIsVerified();

    String getAddress1();

    String getAddress2();

    String getCity();

    String getState();

    String getZip();
}
