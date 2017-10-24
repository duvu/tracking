package com.vd5.tracking.web.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author beou on 9/21/17 04:40
 * @version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceRequest implements Serializable {

    private static final long serialVersionUID = 7294250623424629286L;

    @Size(max = 32)
    private String name;

    private Long accountId;

    @NotNull
    @Size(max = 32)
    private String deviceId;

    @Size(max = 32)
    private String vehicleId;

    @Size(max = 32)
    private String protocol;

    @Size(max = 32)
    private String serivalNumber;

    @Size(max = 32)
    private String modelName;

    private String manufacturerName;

    private String firmwareVerison;

    private String originalCountry;
}
