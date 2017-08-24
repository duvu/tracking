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
@Table(name = _TableName.TABLE_EVENT_DATA)
public class EventData implements Serializable {

    private static final long serialVersionUID = -4305914776247077278L;

    public static final String ID                   = "id";
    public static final String ACCOUNT_ID           = "accountId";
    public static final String DEVICE_ID            = "deviceId";
    public static final String TIMESTAMP            = "timestamp";
    public static final String STATUS_CODE          = "statusCode";


    public static final String CREATED_AT       = "createdAt";
    public static final String UPDATED_AT       = "updatedAt";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ID, nullable = false)
    private Long id;

    @Column(name = ACCOUNT_ID, nullable = false, length = 32)
    private String accountId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = ACCOUNT_ID, referencedColumnName = ACCOUNT_ID, insertable = false, updatable = false)
    private Account account;

    @Column(name = DEVICE_ID, nullable = false, length = 32)
    private String deviceId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = DEVICE_ID, referencedColumnName = DEVICE_ID, insertable = false, updatable = false)
    private Device device;

    @Column(name = CREATED_AT)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = UPDATED_AT)
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
