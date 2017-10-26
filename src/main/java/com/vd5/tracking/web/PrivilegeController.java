package com.vd5.tracking.web;

import com.vd5.tracking.exception.ValidationException;
import com.vd5.tracking.service.PrivilegeService;
import com.vd5.tracking.utils.AuthenticationFacade;
import com.vd5.tracking.web.projection.PrivilegeProjection;
import com.vd5.tracking.web.request.PrivilegeRequest;
import com.vd5.tracking.web.specification.PrivilegeSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @author beou on 10/16/17 18:22
 */
@Slf4j
@RestController
@RequestMapping(value = "/api/privilege")
public class PrivilegeController implements BaseController<PrivilegeRequest, PrivilegeProjection> {

    private PrivilegeService privilegeService;
    private ProjectionFactory projectionFactory;
    private PrivilegeSpecification privilegeSpecification;
    private AuthenticationFacade authenticationFacade;

    @Autowired
    public PrivilegeController(PrivilegeService privilegeService, ProjectionFactory projectionFactory, PrivilegeSpecification privilegeSpecification, AuthenticationFacade authenticationFacade) {
        this.privilegeService = privilegeService;
        this.projectionFactory = projectionFactory;
        this.privilegeSpecification = privilegeSpecification;
        this.authenticationFacade = authenticationFacade;
    }

    @GetMapping
    @PreAuthorize(value = "hasRole('SYSTEM_ADMIN')")
    public Page<PrivilegeProjection> getAll(@RequestParam(name = "search", required = false) String search, Pageable pageable) {
        return privilegeService.getAll(search, pageable).map(x -> projectionFactory.createProjection(PrivilegeProjection.class, x));
    }

    @GetMapping("/all")
    @PreAuthorize(value = "hasRole('SYSTEM_ADMIN')")
    public List<PrivilegeProjection> getAll(@RequestParam(name = "search", required = false) String search) {
        return privilegeService.getAll(search).stream().map(x -> projectionFactory.createProjection(PrivilegeProjection.class, x)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize(value = "hasRole('SYSTEM_ADMIN')")
    public PrivilegeProjection getById(@PathVariable Long id) {
        return projectionFactory.createProjection(PrivilegeProjection.class, privilegeService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize(value = "hasRole('SYSTEM_ADMIN')")
    public PrivilegeProjection create(@RequestBody @Valid PrivilegeRequest request, BindingResult result) {
        log.info("CurrentUser: #" + authenticationFacade.getCurrentUserName());
        if (result.hasErrors())
            throw new ValidationException("Privilege", result.getFieldErrors());
        return  projectionFactory.createProjection(PrivilegeProjection.class, privilegeService.create(request));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize(value = "hasRole('SYSTEM_ADMIN')")
    public void update(@PathVariable Long id, @RequestBody @Valid PrivilegeRequest request, BindingResult result) {
        if (result.hasErrors())
            throw new ValidationException("Privilege", result.getFieldErrors());
        privilegeService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize(value = "hasRole('SYSTEM_ADMIN')")
    public void delete(@PathVariable Long id) {
        privilegeService.delete(id);
    }
}
