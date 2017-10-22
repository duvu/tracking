package com.vd5.tracking.web.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * @author beou on 9/21/17 03:53
 * @version 1.0
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequest implements Serializable {
    private static final long serialVersionUID = -591813592141537331L;

    @NotNull
    @Size(max = 32)
    private String accountId;

    private String password;

    @Size(max = 25)
    private String firstName;

    @Size(max = 25)
    private String lastName;

    private String status;

    @NotNull
    private Long organizationId;

    private Set<Long> privilegeIds;

    @Size(max = 20)
    private String phoneNumber;

    @Size(max = 255)
    private String photoUrl;

    @NotNull
    @Size(max = 128)
    private String emailAddress;

    @Size(max = 128)
    private String addressLine1;

    @Size(max = 128)
    private String addressLine2;

    @Size(max = 512)
    private String notes;
}
