package com.vd5.tracking.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author beou on 8/1/17 03:10
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Device implements Serializable {

    private static final long serialVersionUID = -8426530978816745841L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 32)
    private String name;

    @Column(nullable = false, unique = true, length = 32)
    private String deviceId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "accountId", referencedColumnName = "id")
    private Account account;

    @OneToOne
    @JoinColumn(name = "vehicleId", referencedColumnName = "id")
    private Vehicle vehicle;

    private String ipAddress;

    private Integer port;

    @Column(length = 32)
    private String protocol;

    @Column(length = 32)
    private String serialNumber;

    @Column(length = 32)
    private String modelName;

    @Column(length = 128)
    private String manufacturerName;

    @Column(length = 32)
    private String firmwareVerison;

    @Column(length = 128)
    private String originalCountry;

    @Column(length = 32)
    private String createdBy;

    @Column(length = 32)
    private String updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    @PrePersist
    private void prePersist() {
        this.createdOn = new Date();
    }

    @PreUpdate
    private void preUpdate () {
        this.updatedOn = new Date();
    }
}
