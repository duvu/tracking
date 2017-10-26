package com.vd5.tracking.web;

import com.vd5.tracking.exception.ValidationException;
import com.vd5.tracking.service.DeviceService;
import com.vd5.tracking.web.projection.DeviceProjection;
import com.vd5.tracking.web.request.DeviceRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author beou on 8/1/17 03:10
 */

@Slf4j
@RestController
@RequestMapping(value = "/api/device")
public class DeviceController implements BaseController<DeviceRequest, DeviceProjection> {

    private final DeviceService deviceService;
    private final ProjectionFactory projectionFactory;

    public DeviceController(DeviceService deviceService, ProjectionFactory projectionFactory) {
        this.deviceService = deviceService;
        this.projectionFactory = projectionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    @GetMapping
    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN', 'ADMINISTRATOR', 'MODERATOR', 'USER')")
    public Page<DeviceProjection> getAll(@RequestParam(name = "search", required = false) String search, Pageable pageable) {
        return deviceService.getAll(search, pageable).map(x -> projectionFactory.createProjection(DeviceProjection.class, x));
    }

    @Override
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN', 'ADMINISTRATOR', 'MODERATOR', 'USER')")
    public List<DeviceProjection> getAll(@RequestParam(name = "search", required = false)String search) {
        return deviceService.getAll(search).stream().map(x -> projectionFactory.createProjection(DeviceProjection.class, x)).collect(Collectors.toList());
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN', 'ADMINISTRATOR', 'MODERATOR', 'USER') and hasPermission()")
    public DeviceProjection getById(@PathVariable(value = "id") Long id) {
        return projectionFactory.createProjection(DeviceProjection.class, deviceService.getById(id));
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN', 'ADMINISTRATOR', 'MODERATOR')")
    public DeviceProjection create(@RequestBody @Valid DeviceRequest request, BindingResult result) {
        if (result.hasErrors())
            throw new ValidationException("Device", result.getFieldErrors());
        return projectionFactory.createProjection(DeviceProjection.class, deviceService.create(request));
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN', 'ADMINISTRATOR', 'MODERATOR', 'USER')")
    public void update(@PathVariable(value = "id") Long id, @RequestBody @Valid DeviceRequest request, BindingResult result) {
        if (result.hasErrors())
            throw new ValidationException("Device", result.getFieldErrors());
        deviceService.update(id, request);
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN', 'ADMINISTRATOR', 'MODERATOR')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable(value = "id") Long id) {
        deviceService.delete(id);
    }
}
