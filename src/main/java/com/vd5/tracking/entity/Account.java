package com.vd5.tracking.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * @author beou on 8/1/17 03:09
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable {

    private static final long serialVersionUID = -7003585213284904715L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true, length = 32)
    private String accountId;
    private String password;
    private String firstName;
    private String lastName;
    private Integer status;     //-1: deleted, 0: disabled, 1: activated, 2: not-paid, warning

    private Long roleId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OrganizationId", referencedColumnName = "id")
    private Organization organization;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "AccountPrivilege", joinColumns = @JoinColumn(name = "accountId", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "privilegeId", referencedColumnName = "id"))
    private Set<Privilege> privileges;

    @Column(length = 20)
    private String phoneNumber;

    private String photoUrl;

    @Column(nullable = false, unique = true, length = 128)
    private String emailAddress;

    @Column(length = 128)
    private String addressLine1;

    @Column(length = 128)
    private String addressLine2;

    private String notes;

    private String createdBy;
    private String updatedBy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    //--
    @PrePersist
    private void prePersist() {
        this.createdOn = new Date();
    }

    @PreUpdate
    private void preUpdate () {
        this.updatedOn = new Date();
    }
}
