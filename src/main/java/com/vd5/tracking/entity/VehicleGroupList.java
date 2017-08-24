package com.vd5.tracking.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author beou on 8/1/17 15:40
 * @version 1.0
 */
@Data
@Entity
@Table(name = _TableName.TABLE_VEHICLE_GROUP_LIST)
public class VehicleGroupList {
    private static final String ID              = "id";
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ID, nullable = false)
    private Long id;
}
