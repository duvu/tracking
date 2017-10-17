package com.vd5.tracking.rest.projection;

import java.util.Date;

/**
 * @author beou on 10/16/17 23:10
 */
public interface OrganizationProjection {
    Long getId();
    String getName();
    String getEmailAddress();
    String getPhotoUrl();
    String getPhoneNumber();
    String getAddressLine1();
    String getAddressLine2();
    String getCreatedBy();
    String getUpdatedBy();
    Date getCreatedOn();
    Date getUpdatedOn();
}
