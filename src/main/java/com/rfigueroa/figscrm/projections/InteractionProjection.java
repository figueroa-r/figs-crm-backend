package com.rfigueroa.figscrm.projections;

import java.util.Date;

public interface InteractionProjection {

    Integer getId();
    String getUser();
    Date getInteractionDate();
    String getInteractionDetails();
}
