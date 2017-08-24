package com.vd5.tracking.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author beou on 8/1/17 13:50
 * @version 1.0
 */
@Data
@Entity
@Table(name = _TableName.TABLE_VEHICLE)
public class Vehicle implements Serializable {

    private static final long serialVersionUID = -1853762869456627294L;

    private static final String ID                  = "id";
    private static final String ACCOUNT_ID          = "accountId";
    private static final String DEVICE_ID           = "deviceId";
    private static final String BRAND               = "brand";
    private static final String MODEL               = "model";
    private static final String YEAR_MADE           = "yearMade";
    private static final String LICENSE_PLATE       = "licensePlate";

    private static final String CREATED_AT          = "createdAt";
    private static final String UPDATED_AT          = "updatedAt";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ID, nullable = false)
    private Long id;

    @Column(name = DEVICE_ID)
    private Long deviceId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DEVICE_ID, referencedColumnName = ID, insertable = false, updatable = false)
    private Device device;

    @Column(name = BRAND)
    private String brand;
}
