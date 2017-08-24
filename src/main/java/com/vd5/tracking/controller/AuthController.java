package com.vd5.tracking.controller;

import com.vd5.tracking.model.Response;
import com.vd5.tracking.model.request.AuthModel;
import com.vd5.tracking.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author beou on 8/21/17 19:12
 * @version 1.0
 */

@Slf4j
@RestController
@RequestMapping(value = {"/api/auth", "/api/authenticate"})
public class AuthController {

    final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping(value = {"", "/"}, method = RequestMethod.POST)
    public ResponseEntity authenticate (@RequestBody AuthModel authModel) {

        log.info(authModel.toString());

        Response response = authService.authenticate(authModel.getUserName(), authModel.getPassword());
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
