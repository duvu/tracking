package com.vd5.tracking.rest;

import com.vd5.tracking.rest.projection.DeviceProjection;
import com.vd5.tracking.rest.request.DeviceRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * @author beou on 8/1/17 03:10
 * @version 1.0
 */
public class DeviceController implements BaseController<DeviceRequest, DeviceProjection> {
    @Override
    public Page<DeviceProjection> getAll(String search, Pageable pageable) {
        return null;
    }

    @Override
    public List<DeviceProjection> getAll(String search) {
        return null;
    }

    @Override
    public DeviceProjection getById(Long id) {
        return null;
    }

    @Override
    public DeviceProjection create(DeviceRequest request, BindingResult result) {
        return null;
    }

    @Override
    public void update(Long id, DeviceRequest request, BindingResult result) {

    }

    @Override
    public void delete(Long id) {

    }
}
