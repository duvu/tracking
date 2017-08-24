package com.vd5.tracking.controller;

import com.vd5.tracking.entity.Account;
import com.vd5.tracking.model.Response;
import com.vd5.tracking.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author beou on 8/1/17 03:10
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/api/account")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(value = {"", "/", "/add_account"}, method = RequestMethod.POST)
    public ResponseEntity<Response> addNewAccount(@RequestBody Account account) {
        Response bodyData = accountService.add(account);

        return new ResponseEntity<Response>(bodyData, HttpStatus.OK);
    }
}
