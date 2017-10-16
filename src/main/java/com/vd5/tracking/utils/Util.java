package com.vd5.tracking.utils;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author beou on 10/16/17 19:32
 */
public class Util {
    public static String getCurrentUsername() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
