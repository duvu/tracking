package com.vd5.tracking.web;

import com.vd5.tracking.exception.ValidationException;
import com.vd5.tracking.service.PrivilegeService;
import com.vd5.tracking.web.projection.PrivilegeProjection;
import com.vd5.tracking.web.request.PrivilegeRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author beou on 10/16/17 18:22
 */
@RestController
@RequestMapping(value = "/api/privilege")
public class PrivilegeController implements BaseController<PrivilegeRequest, PrivilegeProjection> {

    private final PrivilegeService privilegeService;
    private final ProjectionFactory projectionFactory;

    public PrivilegeController(PrivilegeService privilegeService, ProjectionFactory projectionFactory) {
        this.privilegeService = privilegeService;
        this.projectionFactory = projectionFactory;
    }

    @GetMapping
    public Page<PrivilegeProjection> getAll(@RequestParam(name = "search", required = false) String search, Pageable pageable) {
        return null;
    }

    @GetMapping("/all")
    public List<PrivilegeProjection> getAll(@RequestParam(name = "search", required = false) String search) {
        return null;
    }

    @GetMapping("/{id}")
    public PrivilegeProjection getById(@PathVariable Long id) {
        return null;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PrivilegeProjection create(@RequestBody @Valid PrivilegeRequest request, BindingResult result) {
        if (result.hasErrors())
        throw new ValidationException("Account", result.getFieldErrors());
        return  projectionFactory.createProjection(PrivilegeProjection.class, privilegeService.create(request));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody @Valid PrivilegeRequest request, BindingResult result) {
        if (result.hasErrors())
            throw new ValidationException("Account", result.getFieldErrors());
        privilegeService.update(id, request);
    }

    @DeleteMapping("/id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        privilegeService._delete(id);
    }

}
