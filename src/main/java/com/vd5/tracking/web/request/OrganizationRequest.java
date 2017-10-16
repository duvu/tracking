package com.vd5.tracking.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author beou on 10/16/17 19:57
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrganizationRequest implements Serializable{
    @NotNull
    private String name;
    @NotNull
    private String emailAddress;
    private String phoneNumber;
    private String addressLine1;
    private String addressLine2;
}
