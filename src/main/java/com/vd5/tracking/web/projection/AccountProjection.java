package com.vd5.tracking.web.projection;

import com.vd5.tracking.model.AccountStatus;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

/**
 * @author beou on 9/21/17 03:51
 * @version 1.0
 */
public interface AccountProjection {
    Long getId();
    String getAccountId();

    String getFirstName();
    String getLastName();
    AccountStatus getStatus();
    @Value(value = "#{target.getOrganization() != null ? target.getOrganization().getId() : null}")
    Long getOrganizationId();
    @Value(value = "#{target.getOrganization() != null ? target.getOrganization().getName() : null}")
    String getOrganizationName();
    String getPhoneNumber();
    String getPhotoUrl();
    String getEmailAddress();
    String getAddressLine1();
    String getAddressLine2();
    String getNotes();
    String getCreatedBy();
    String getUpdatedBy();
    Date getCreatedOn();
    Date getUpdatedOn();
}
