package com.vd5.tracking.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author beou on 8/1/17 15:40
 * @version 1.0
 */
@Data
@Entity
@Table(name = "VehicleGroupList")
public class VehicleGroupList {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
}
