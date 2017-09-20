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
@Table(name = "VehicleGroup")
public class VehicleGroup implements Serializable {

    private static final long serialVersionUID = -6248071468469723968L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
}
