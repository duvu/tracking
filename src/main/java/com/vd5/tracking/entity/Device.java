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
    @Column(name = "id")
    private Long id;

    @Column(name = "accountId", nullable = false, length = 32)
    private String accountId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accountId", referencedColumnName = "accountId", insertable = false, updatable = false)
    private Account account;

    @Column(name = "deviceId", nullable = false, length = 32)
    private String deviceId;

    @Column(name = "createdOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Column(name = "updatedOn")
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
