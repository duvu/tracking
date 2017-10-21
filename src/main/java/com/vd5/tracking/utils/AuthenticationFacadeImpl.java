package com.vd5.tracking.utils;

import com.vd5.tracking.auth.MyPrincipal;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author beou on 10/17/17 22:21
 */
@Component
public class AuthenticationFacadeImpl implements AuthenticationFacade {

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    @Override
    public String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        }
        return null;
    }

    @Override
    public Boolean isSysAdmin() {
        List<String> roleList = getRoleList();
        return roleList.contains(RolesList.ROLE_SYSTEM_ADMIN);
    }

    @Override
    public Boolean isAdmin() {
        List<String> roleList = getRoleList();
        return roleList.contains(RolesList.ROLE_ADMINISTRATOR);
    }

    @Override
    public Boolean isModerator() {
        return getRoleList().contains(RolesList.ROLE_MODERATOR);
    }

    @Override
    public Boolean isUser() {
        return getRoleList().contains(RolesList.ROLE_USER);
    }


    @Override
    public Long getAccountId() {
        MyPrincipal principal = (MyPrincipal)getAuthentication().getPrincipal();
        return principal.getAccountId();
    }

    @Override
    public Long getOrganizationId() {
        MyPrincipal principal = (MyPrincipal) getAuthentication().getPrincipal();
        return principal.getOrganizationId();
    }

    private List<String> getRoleList() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }
}
