package com.vd5.tracking.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author beou on 8/1/17 14:38
 */
@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Privilege implements Serializable{

    private static final long serialVersionUID = 3330897676342809663L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 128)
    private String name;

    @Column(length = 255)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE, CascadeType.REFRESH })
    @JoinTable(name = "PrivilegeMenu", joinColumns = @JoinColumn(name = "privilegeId", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "menuId", referencedColumnName = "id"))
    @OrderBy(value = "ordered asc")
    private List<Menu> menuList;


    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;

    @Column(length = 32)
    private String createdBy;

    @Column(length = 32)
    private String updatedBy;

    @PrePersist
    private void prePersist() {
        createdOn = new Date();
    }

    @PreUpdate
    private void preUpdate() {
        updatedOn = new Date();
    }
}
