package com.vd5.tracking.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author beou on 10/23/17 15:37
 */
public class DeviceMessageQueue implements Serializable {

    private static final long serialVersionUID = 7462316207327659350L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

}
