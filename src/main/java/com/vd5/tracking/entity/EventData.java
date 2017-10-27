package com.vd5.tracking.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.core.geo.GeoPoint;

import javax.persistence.Id;
import java.util.Date;

/**
 * @author beou on 10/27/17 13:25
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "event_data", type = "event")
public class EventData {

    @Id
    private Long id;

    private String accountId;
    private String deviceId;

    private String status;  //power-on/power-off/closed-door/heartbeat/fixed-location...
    private Date gpsDate;
    private GeoPoint location;
    private double speedKph;
    private double direction;
    private double altitude;
    private String address;

    private double batteryLevel;
    private double voltage;
    private double signalStrength;

    private Date createdOn;
    private Date updatedOn;
    private String createdBy;
    private String updatedBy;
}
