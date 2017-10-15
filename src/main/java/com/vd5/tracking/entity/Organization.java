package com.vd5.tracking.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author beou on 10/15/17 15:25
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Organization implements Serializable {
    private static final long serialVersionUID = -7888420326276111929L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false, unique = true)
    private String emailAddress;
    private String phoneNumber;
    private String addressLine1;
    private String addressLine2;
    private String createdBy;
    private String updatedBy;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    @PrePersist
    private void prePersit() {
        createdOn = new Date();
    }

    @PreUpdate
    private void preUpdate() {
        updatedOn = new Date();
    }
}
