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

    @Column(nullable = false, unique = true, length = 128)
    private String name;

    @Column(nullable = false, unique = true, length = 128)
    private String emailAddress;

    @Column(length = 254)
    private String photoUrl;

    @Column(length = 20)
    private String phoneNumber;

    @Column(length = 128)
    private String addressLine1;

    @Column(length = 128)
    private String addressLine2;

    @Column(length = 32)
    private String createdBy;

    @Column(length = 32)
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
