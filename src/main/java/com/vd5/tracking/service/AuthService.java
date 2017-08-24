package com.vd5.tracking.service;

import com.vd5.tracking.model.Response;
import org.springframework.stereotype.Service;

/**
 * @author beou on 8/22/17 15:20
 * @version 1.0
 */
@Service
public interface AuthService {
    public Response authenticate(String userName, String password);
}
