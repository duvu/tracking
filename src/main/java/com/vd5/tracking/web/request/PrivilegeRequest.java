package com.vd5.tracking.web.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author beou on 10/16/17 18:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrivilegeRequest implements Serializable{
    private static final long serialVersionUID = 5357559035369504422L;
    @NotNull
    private String name;
    private String description;
}
