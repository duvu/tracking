package com.vd5.tracking.entity;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author beou on 8/1/17 14:56
 * @version 1.0
 */
@Data
@Entity
@Table(name = "Permission")
public class Permission implements GrantedAuthority {

    private static final long serialVersionUID = 7687698993542918351L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "roleId", nullable = false)
    private Long roleId;

    @Column(name = "className")
    private String className;

    @Column(name = "fieldName")
    private String fieldName;

    @Column(name = "accessLevel")
    private int accessLevel; //0,1,2,3 NONE, READ, UPDATE, ALL

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "roleId", referencedColumnName = "id", insertable = false, updatable = false)
    private Role role;

    @Override
    public String getAuthority() {
        StringBuffer sb = new StringBuffer();
        sb.append(className).append(":").append(fieldName).append(":").append(accessLevel);
        return sb.toString();
    }
}
