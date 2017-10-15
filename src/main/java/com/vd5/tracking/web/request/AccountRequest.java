package com.vd5.tracking.web.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

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
}
