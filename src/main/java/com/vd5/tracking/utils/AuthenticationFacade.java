package com.vd5.tracking.utils;

import org.springframework.security.core.Authentication;

/**
 * @author beou on 10/17/17 22:21
 */
public interface AuthenticationFacade {
    Authentication getAuthentication();
    String getCurrentUserName();
}
