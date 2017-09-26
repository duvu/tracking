package com.vd5.tracking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author beou on 8/1/17 03:09
 * @version 1.0
 */
@Entity
@Data
@Builder
@Table(name = "Account")
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account implements Serializable {

    private static final long serialVersionUID = -8962663234205282943L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "accountId", nullable = false, length = 32)
    private String accountId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "managerId", referencedColumnName = "id", insertable = false, updatable = false)
    private Account manager;

    @Column(name = "password")
    private String password;

    @Column(name = "displayName")
    private String displayName;

    @Column(name = "status", nullable = false)
    private Integer status;

    @Column(name = "roleId")
    private Long roleId;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "roleId", referencedColumnName = "id", insertable = false, updatable = false)
    private Role role;

    //-- created-role
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id", referencedColumnName = "accountId")
    private List<Role> roles;


    @Column(name = "phone", length = 20)
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "addressLine1", length = 128)
    private String addressLine1;

    @Column(name = "addressLine2", length = 128)
    private String addressLine2;

    @Column(name = "notes", length = 512)
    private String notes;

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
        this.status = Status.Account.ACTIVATED.getValue();
    }

    @PreUpdate
    private void preUpdate () {
        this.updatedAt = new Date();
    }
}
