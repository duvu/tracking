package com.vd5.tracking.web.projection;

import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

/**
 * @author beou on 9/21/17 04:43
 * @version 1.0
 */
public interface DeviceProjection {
    Long getId();
    String getName();
    String getDeviceId();

    @Value(value = "#{target.getAccount() != null ? target.getAccount().getId() : null}")
    Long getAccountId();

    @Value(value = "#{target.getAccount() != null ? target.getAccount().getAccountId(): null}")
    String getAccountName();

    @Value(value = "#{target.getVehicle() != null ? target.getVehicle.getId() : null }")
    Long getVehicleId();

    @Value(value = "#{target.getVehicle() != null ? target.getVehicle.getName() : null }")
    Long getVehicleName();

    String getIpAddress();

    Integer getPort();

    String getProtocol();

    String getSerialNumber();

    String getModelName();

    String getManufacturerName();

    String getFirmwareVerison();

    String getOriginalCountry();

    String getCreatedBy();

    String getUpdatedBy();

    Date getCreatedOn();

    Date getUpdatedOn();
}
