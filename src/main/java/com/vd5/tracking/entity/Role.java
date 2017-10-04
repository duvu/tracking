package com.vd5.tracking.entity;

import lombok.Builder;
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
@Builder
@Entity
@Table(name = "Role")
public class Role implements GrantedAuthority {

    private static final long serialVersionUID = 3330897676342809663L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "accountId")
    private Long accountId;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "accountId", referencedColumnName = "id", insertable = false, updatable = false)
    private Account account;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @OneToMany (mappedBy = "roleId", cascade = CascadeType.ALL)
    private List<Permission> permissions;

    @Column(name = "createdAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "updatedAt")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @PrePersist
    private void prePersist() {
        this.createdAt = new Date();
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
