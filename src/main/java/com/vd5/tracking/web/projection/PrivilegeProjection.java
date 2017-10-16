package com.vd5.tracking.web.projection;

import java.util.Date;

/**
 * @author beou on 10/16/17 18:23
 */
public interface PrivilegeProjection {
    public Long getId();
    public String getName();
    public String getDescription();
    public Date getCreatedOn();
    public Date getUpdatedOn();
    public String createdBy();
    public String updatedBy();
}
