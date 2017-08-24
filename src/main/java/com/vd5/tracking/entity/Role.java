package com.vd5.tracking.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author beou on 8/1/17 14:38
 * @version 1.0
 */
@Data
@Entity
@Table(name = _TableName.TABLE_ROLE)
public class Role implements GrantedAuthority {

    public static final String ID               = "id";
    public static final String ROLE_ID          = "roleId";
    public static final String ACCOUNT_ID       = "accountId";
    public static final String NAME             = "name";
    public static final String DESCRIPTION      = "description";
    public static final String CREATED_AT       = "createdAt";
    public static final String UPDATED_AT       = "updatedAt";

    private static final long serialVersionUID = 3330897676342809663L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ID, nullable = false)
    private Long id;

    @Column(name = ACCOUNT_ID)
    private Long accountId;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = ACCOUNT_ID, referencedColumnName = ID, insertable = false, updatable = false)
    private Account account;


    @Column(name = NAME)
    private String name;

    @Column(name = DESCRIPTION)
    private String description;

    @OneToMany (mappedBy = ROLE_ID, cascade = CascadeType.ALL)
    private List<Permission> permissions;

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
    private void preUpdate() {
        this.updatedAt = new Date();
    }

    @Override
    public String getAuthority() {
        return name;
    }
}
