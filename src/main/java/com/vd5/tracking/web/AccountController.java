package com.vd5.tracking.web;

import com.vd5.tracking.entity.Account;
import com.vd5.tracking.exception.ValidationException;
import com.vd5.tracking.service.AccountService;
import com.vd5.tracking.utils.AuthenticationFacade;
import com.vd5.tracking.web.projection.AccountProjection;
import com.vd5.tracking.web.request.AccountRequest;
import com.vd5.tracking.web.specification.AccountSpecificationHelper;
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
 * @author beou on 8/1/17 03:10
 * @version 1.0
 */

@Slf4j
@RestController
@RequestMapping(value = "/api/account")
public class AccountController implements BaseController<AccountRequest, AccountProjection> {

    private AccountService accountService;
    private AccountSpecificationHelper accountSpecification;

    private ProjectionFactory projectionFactory;
    private AuthenticationFacade authenticationFacade;

    @Autowired
    public AccountController(AccountService accountService, AccountSpecificationHelper accountSpecification,
                             ProjectionFactory projectionFactory, AuthenticationFacade authenticationFacade) {
        this.accountService = accountService;
        this.accountSpecification = accountSpecification;
        this.projectionFactory = projectionFactory;
        this.authenticationFacade = authenticationFacade;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN', 'ADMINISTRATOR', 'MODERATOR', 'USER')")
    public Page<AccountProjection> getAll(@RequestParam(name = "search", required = false) String search, Pageable pageable) {
        return accountService.getAll(search, pageable).map(x -> projectionFactory.createProjection(AccountProjection.class, x));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN', 'ADMINISTRATOR', 'MODERATOR', 'USER')")
    public List<AccountProjection> getAll(@RequestParam(name = "search", required = false) String search) {
        return accountService.getAll(search).stream().map(x -> projectionFactory.createProjection(AccountProjection.class, x)).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN', 'ADMINISTRATOR', 'MODERATOR', 'USER') and hasPermission()")
    public AccountProjection getById(@PathVariable Long id) {
        return projectionFactory.createProjection(AccountProjection.class, accountService.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN', 'ADMINISTRATOR', 'MODERATOR')")
    public AccountProjection create(@RequestBody @Valid AccountRequest request, BindingResult result) {
        if (result.hasErrors())
            throw new ValidationException("Account", result.getFieldErrors());
        return projectionFactory.createProjection(AccountProjection.class, accountService.create(request));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN', 'ADMINISTRATOR', 'MODERATOR', 'USER')")
    public void update(@PathVariable Long id, @RequestBody @Valid AccountRequest request, BindingResult result) {
        if (result.hasErrors())
            throw new ValidationException("Account", result.getFieldErrors());
        accountService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN', 'ADMINISTRATOR', 'MODERATOR')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        accountService.delete(id);
    }
}
