package com.vd5.tracking.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author beou on 8/1/17 03:09
 * @version 1.0
 */

@Entity
@Data
@Table(name = "EventData")
public class EventData implements Serializable {

    private static final long serialVersionUID = -4305914776247077278L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "accountId", nullable = false, length = 32)
    private String accountId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accountId", referencedColumnName = "accountId", insertable = false, updatable = false)
    private Account account;

    @Column(name = "deviceId", nullable = false, length = 32)
    private String deviceId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "deviceId", referencedColumnName = "deviceId", insertable = false, updatable = false)
    private Device device;

    @Column(name = "createdAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;


    @PrePersist
    private void prePersist() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    @PreUpdate
    private void preUpdate () {
        this.updatedAt = new Date();
    }
}
