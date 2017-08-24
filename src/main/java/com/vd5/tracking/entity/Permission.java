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
@Table(name = _TableName.PERMISSION)
public class Permission implements GrantedAuthority {

    public static final String ID               = "id";
    public static final String ROLE_ID          = "roleId";
    private static final String CLASS_NAME      = "className";
    private static final String FIELD_NAME      = "fieldName";
    private static final String ACCESS_LEVEL    = "accessLevel";

    private static final long serialVersionUID = 7687698993542918351L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ID, nullable = false)
    private Long id;

    @Column(name = ROLE_ID, nullable = false)
    private Long roleId;

    @Column(name = CLASS_NAME)
    private String className;

    @Column(name = FIELD_NAME)
    private String fieldName;

    @Column(name = ACCESS_LEVEL)
    private int accessLevel; //0,1,2,3 NONE, READ, UPDATE, ALL

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = ROLE_ID, referencedColumnName = ID, insertable = false, updatable = false)
    private Role role;

    @Override
    public String getAuthority() {
        StringBuffer sb = new StringBuffer();
        sb.append(className).append(":").append(fieldName).append(":").append(accessLevel);
        return sb.toString();
    }
}
