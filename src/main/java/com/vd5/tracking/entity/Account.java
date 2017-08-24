package com.vd5.tracking.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

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
@Table(name = _TableName.TABLE_ACCOUNT)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Account implements Serializable {

    private static final long serialVersionUID = -8962663234205282943L;

    public static final String ID               = "id";
    public static final String ACCOUNT_ID       = "accountId";
    public static final String MANAGER_ID       = "managerId";
    public static final String PASSWORD         = "password";
    public static final String DISPLAY_NAME     = "displayName";
    public static final String STATUS           = "status";

    public static final String ROLE_ID          = "roleId";

    public static final String PHONE            = "phone";
    public static final String EMAIL            = "email";
    public static final String ADDRESS_LINE1    = "addressLine1";
    public static final String ADDRESS_LINE2    = "addressLine2";
    public static final String ADDRESS_CITY     = "addressCity";
    public static final String POSTCODE         = "postCode";
    public static final String ADDRESS_STATE    = "addressState";
    public static final String COUNTRY          = "country";

    public static final String NOTES            = "notes";

    public static final String ALLOW_NOTIFY     = "allowNotify";
    public static final String MAXIMUM_DEVICE   = "maximumDevice";
    public static final String TOTAL_PING_COUNT = "totalPingCount";
    public static final String MAX_PING_COUNT   = "maxPingCount";
    public static final String ALLOW_SMS        = "allowSMS";
    public static final String MAX_SMS_COUNT    = "maxSMSCount";

    public static final String SMS_PROPERTIES   = "SMSProperties";
    public static final String EMAIL_PROPERTIES = "emailProperties";

    public static final String EXPIRATION_TIME  = "expirationTime";
    public static final String TIMEZONE         = "timezone";
    public static final String LOCALIZATION     = "localization";

    public static final String LAST_LOGGED_IN   = "lastLoggedIn";

    public static final String DATA_PUSH_URL    = "dataPushUrl";
    //--units

    public static final String CREATED_AT       = "createdAt";
    public static final String UPDATED_AT       = "updatedAt";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ID, nullable = false)
    private Long id;

    //-- as userName
    @Column(name = ACCOUNT_ID, nullable = false, length = 32)
    private String accountId;

    @Column(name = MANAGER_ID, length = 32)
    private Long managerId;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = MANAGER_ID, referencedColumnName = ID, insertable = false, updatable = false)
    private Account manager;

    @JsonIgnore
    @Column(name = PASSWORD)
    private String password;

    @Column(name = DISPLAY_NAME)
    private String displayName;

    @Column(name = STATUS, nullable = false)
    private Integer status;

    @Column(name = ROLE_ID)
    private Long roleId;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = ROLE_ID, referencedColumnName = ID, insertable = false, updatable = false)
    private Role role;

    //-- created-role
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = ID, referencedColumnName = ACCOUNT_ID)
    private List<Role> roles;


    @Column(name = PHONE, length = 20)
    private String phone;

    @Column(name = EMAIL)
    private String email;

    @Column(name = ADDRESS_LINE1, length = 128)
    private String addressLine1;

    @Column(name = ADDRESS_LINE2, length = 128)
    private String addressLine2;

    @Column(name = NOTES, length = 512)
    private String notes;

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
        this.status = Status.Account.ACTIVATED.getValue();
    }

    @PreUpdate
    private void preUpdate () {
        this.updatedAt = new Date();
    }
}
