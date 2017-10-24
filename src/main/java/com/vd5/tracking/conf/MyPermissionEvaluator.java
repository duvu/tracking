package com.vd5.tracking.conf;

import com.vd5.tracking.auth.MyPrincipal;
import com.vd5.tracking.model.Permission;
import com.vd5.tracking.utils.RolesList;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author beou on 10/15/17 05:40
 */
public class MyPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if ((authentication == null) || (targetDomainObject == null) || !(permission instanceof Permission)){
            return false;
        }
        String targetType = targetDomainObject.getClass().getSimpleName().toUpperCase();

        String perm = ((String) permission).toUpperCase();
        MyPrincipal principal = (MyPrincipal) authentication.getPrincipal();
        Long organizationId = principal.getOrganizationId();
        Long accountId = principal.getAccount().getId();

        List<String> roleList = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        if (roleList.contains(RolesList.ROLE_SYSTEM_ADMIN)) {
            return true;
        } else if (roleList.contains(RolesList.ROLE_ADMINISTRATOR)) {
            //-- return #true only same organization
            return adminAccessible(organizationId, accountId,  targetDomainObject);
        } else if (roleList.contains(RolesList.ROLE_MODERATOR)) {
            //--
            return hasPrivilege(authentication, targetType, perm);
        } else if (roleList.contains(RolesList.ROLE_USER)) {
            //-- normal user
            return hasPrivilege(authentication, targetType, perm);
        } else {
            return hasPrivilege(authentication, targetType.toUpperCase(), perm);
        }
    }



    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        if ((authentication == null) || (targetType == null) || !(permission instanceof String)) {
            return false;
        }
        return hasPrivilege(authentication, targetType.toUpperCase(),
                permission.toString().toUpperCase());
    }

    private boolean adminAccessible(Long organizationId, Long accountId, Object targetDomainObject) {
        return true;
    }

    private boolean hasPrivilege(Authentication auth, String targetType, String permission) {
        for (GrantedAuthority grantedAuth : auth.getAuthorities()) {
            if (grantedAuth.getAuthority().startsWith(targetType)) {
                if (grantedAuth.getAuthority().contains(permission)) {
                    return true;
                }
            }
        }
        return false;
    }
}
