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
@Table(name = "Vehicle")
public class Vehicle implements Serializable {

    private static final long serialVersionUID = -1853762869456627294L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "deviceId")
    private Long deviceId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deviceId", referencedColumnName = "id", insertable = false, updatable = false)
    private Device device;

    @Column(name = "brand")
    private String brand;
}
