package com.vd5.tracking.web.controller;

import com.vd5.tracking.entity.Account;
import com.vd5.tracking.exception.ValidationException;
import com.vd5.tracking.model.Response;
import com.vd5.tracking.service.AccountService;
import com.vd5.tracking.web.projection.AccountProjection;
import com.vd5.tracking.web.request.AccountRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author beou on 8/1/17 03:10
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/api/account")
public class AccountController {

    private final AccountService accountService;

    private final ProjectionFactory projectionFactory;

    @Autowired
    public AccountController(AccountService accountService, ProjectionFactory projectionFactory) {
        this.accountService = accountService;
        this.projectionFactory = projectionFactory;
    }

    @PostMapping
    public AccountProjection addNewAccount(@RequestBody @Valid AccountRequest accountRequest, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            throw new ValidationException("Account", bindingResult.getFieldErrors());

        Account account = accountService.add(accountRequest);
        return projectionFactory.createProjection(AccountProjection.class, account);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Response> getAccount(@PathVariable(value = "id") Long id) {
        Response bodyData = accountService.getById(id);
        return new ResponseEntity<Response>(bodyData, HttpStatus.OK);
    }
}
