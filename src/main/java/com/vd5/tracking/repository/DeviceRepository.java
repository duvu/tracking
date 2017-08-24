package com.vd5.tracking.repository;

import com.vd5.tracking.entity.Device;
import org.springframework.data.repository.CrudRepository;

/**
 * @author beou on 8/1/17 03:32
 * @version 1.0
 */
public interface DeviceRepository extends CrudRepository<Device, Long> {
}
