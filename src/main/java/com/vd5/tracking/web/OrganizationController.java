package com.vd5.tracking.web;

import com.vd5.tracking.exception.ValidationException;
import com.vd5.tracking.service.OrganizationService;
import com.vd5.tracking.web.projection.OrganizationProjection;
import com.vd5.tracking.web.request.OrganizationRequest;
import com.vd5.tracking.web.specification.OrganizationSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author beou on 10/16/17 23:10
 */

@Slf4j
@RestController
@RequestMapping(value = "/api/organization")
public class OrganizationController implements BaseController<OrganizationRequest, OrganizationProjection> {

    private final OrganizationService organizationService;
    private final OrganizationSpecification organizationSpecification;
    private final ProjectionFactory projectionFactory;

    public OrganizationController(OrganizationService organizationService,
                                  OrganizationSpecification organizationSpecification,
                                  ProjectionFactory projectionFactory) {
        this.organizationService = organizationService;
        this.organizationSpecification = organizationSpecification;
        this.projectionFactory = projectionFactory;
    }

    @Override
    @GetMapping
    @PreAuthorize(value = "hasRole('SYSTEM_ADMIN')")
    public Page<OrganizationProjection> getAll(@RequestParam(required = false) String search, Pageable pageable) {
        return organizationService.getAll(search, pageable).map(x -> projectionFactory.createProjection(OrganizationProjection.class, x));
    }

    @Override
    @GetMapping("/all")
    @PreAuthorize(value = "hasRole('SYSTEM_ADMIN')")
    public List<OrganizationProjection> getAll(@RequestParam(required = false) String search) {
        return organizationService.getAll(search).stream().map(x -> projectionFactory.createProjection(OrganizationProjection.class, x)).collect(Collectors.toList());
    }

    @Override
    @GetMapping("/{id}")
    @PreAuthorize(value = "hasRole('SYSTEM_ADMIN')")
    public OrganizationProjection getById(@PathVariable Long id) {
        return projectionFactory.createProjection(OrganizationProjection.class, organizationService.getById(id));
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize(value = "hasRole('SYSTEM_ADMIN')")
    public OrganizationProjection create(@RequestBody @Valid OrganizationRequest request, BindingResult result) {
        if (result.hasErrors())
            throw new ValidationException("Organization", result.getFieldErrors());
        return projectionFactory.createProjection(OrganizationProjection.class, organizationService.create(request));
    }

    @Override
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize(value = "hasRole('SYSTEM_ADMIN')")
    public void update(@PathVariable Long id, @RequestBody @Valid OrganizationRequest request, BindingResult result) {
        if (result.hasErrors())
            throw new ValidationException("Organization", result.getFieldErrors());
        organizationService.update(id, request);
    }

    @Override
    @DeleteMapping("/{id}")
    @PreAuthorize(value = "hasRole('SYSTEM_ADMIN')")
    public void delete(@PathVariable Long id) {
        organizationService.delete(id);
    }
}
