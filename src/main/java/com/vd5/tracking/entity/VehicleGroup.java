package com.vd5.tracking.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author beou on 8/1/17 15:40
 * @version 1.0
 */
@Data
@Entity
@Table(name = _TableName.TABLE_VEHICLE_GROUP)
public class VehicleGroup implements Serializable {

    private static final long serialVersionUID = -6248071468469723968L;

    public static final String ID                   = "id";
    public static final String ACCOUNT_ID           = "accountId";
    public static final String NAME                 = "name";
    public static final String DESCRIPTION          = "description";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ID, nullable = false)
    private Long id;
}
