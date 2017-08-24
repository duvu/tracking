package com.vd5.tracking.service.impl;

import com.vd5.tracking.model.Response;
import com.vd5.tracking.model.ResultCode;
import com.vd5.tracking.service.AuthService;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author beou on 8/22/17 15:20
 * @version 1.0
 */
@Component
public class AuthServiceImpl implements AuthService {
    @Override
    public Response authenticate(String userName, String password) {
        return new Response(ResultCode.SUCCESS, null, "Internal Error");
    }
}
