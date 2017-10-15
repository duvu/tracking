package com.vd5.tracking.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author beou on 10/15/17 23:49
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountPrivilege implements Serializable {
    private static final long serialVersionUID = 6872868071534025167L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private Long accountId;

    @Column
    private Long privilegeId;
}
