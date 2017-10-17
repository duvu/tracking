package com.vd5.tracking.rest;

import com.vd5.tracking.exception.ValidationException;
import com.vd5.tracking.service.AccountService;
import com.vd5.tracking.rest.projection.AccountProjection;
import com.vd5.tracking.rest.request.AccountRequest;
import com.vd5.tracking.rest.specification.AccountSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author beou on 8/1/17 03:10
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/api/account")
public class AccountController implements BaseController<AccountRequest, AccountProjection> {

    private final AccountService accountService;
    private final AccountSpecification accountSpecification;

    private final ProjectionFactory projectionFactory;

    @Autowired
    public AccountController(AccountService accountService, AccountSpecification accountSpecification, ProjectionFactory projectionFactory) {
        this.accountService = accountService;
        this.accountSpecification = accountSpecification;
        this.projectionFactory = projectionFactory;
    }

    @GetMapping
    public Page<AccountProjection> getAll(@RequestParam(name = "search", required = false) String search, Pageable pageable) {
        return accountService.getAll(accountSpecification.search(search), pageable).map(x -> projectionFactory.createProjection(AccountProjection.class, x));
    }

    @GetMapping("/all")
    public List<AccountProjection> getAll(@RequestParam(name = "search", required = false) String search) {
        return accountService.getAll(accountSpecification.search(search)).stream().map(x -> projectionFactory.createProjection(AccountProjection.class, x)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AccountProjection getById(@PathVariable Long id) {
        return projectionFactory.createProjection(AccountProjection.class, accountService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AccountProjection create(@RequestBody @Valid AccountRequest request, BindingResult result) {
        if (result.hasErrors())
            throw new ValidationException("Account", result.getFieldErrors());
        return projectionFactory.createProjection(AccountProjection.class, accountService.create(request));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id, @RequestBody @Valid AccountRequest request, BindingResult result) {
        if (result.hasErrors())
            throw new ValidationException("Account", result.getFieldErrors());
        accountService.update(id, request);
    }

    @DeleteMapping("/id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        accountService.delete(id);
    }
}