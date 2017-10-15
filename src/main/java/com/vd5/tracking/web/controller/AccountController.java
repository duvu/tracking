package com.vd5.tracking.web.controller;

import com.vd5.tracking.exception.ValidationException;
import com.vd5.tracking.service.AccountService;
import com.vd5.tracking.web.projection.AccountProjection;
import com.vd5.tracking.web.request.AccountRequest;
import com.vd5.tracking.web.specification.AccountSpecification;
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
 * @author beou on 8/1/17 03:10
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/api/account")
public class AccountController {

    private final AccountService accountService;
    private final AccountSpecification accountSpecification;

    private final ProjectionFactory projectionFactory;

    @Autowired
    public AccountController(AccountService accountService, AccountSpecification accountSpecification, ProjectionFactory projectionFactory) {
        this.accountService = accountService;
        this.accountSpecification = accountSpecification;
        this.projectionFactory = projectionFactory;
    }

    // 1. search & sort & pagination
    // 2. get all & pagination
    // 3. get specific
    // 4. create (return CREATED)
    // 5. update
    // 6. delete

    @GetMapping
    public Page<AccountProjection> getAll(@RequestParam(name = "search", required = false) String search, Pageable pageable) {
        return accountService.searchAndSort(accountSpecification.search(search), pageable).map(x -> projectionFactory.createProjection(AccountProjection.class, x));
    }

    @GetMapping("/all")
    public List<AccountProjection> getAll(@RequestParam(name = "search", required = false) String search) {
        return accountService.searchAndSort(accountSpecification.search(search)).stream().map(x -> projectionFactory.createProjection(AccountProjection.class, x)).collect(Collectors.toList());
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

    @PutMapping("/id")
    public AccountProjection update(@PathVariable Long id, @RequestBody @Valid AccountRequest request, BindingResult result) {
        if (result.hasErrors()) {
            throw new ValidationException("Account", result.getFieldErrors());
        }
        return projectionFactory.createProjection(AccountProjection.class, accountService.update(id, request));
    }

    @DeleteMapping("/id")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        accountService.delete(id);
    }
}
