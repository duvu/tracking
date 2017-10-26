package com.vd5.tracking.auth;

import com.vd5.tracking.entity.Account;
import com.vd5.tracking.entity.Menu;
import com.vd5.tracking.entity.Organization;
import com.vd5.tracking.entity.Privilege;
import com.vd5.tracking.model.AccountStatus;
import com.vd5.tracking.utils.RolesList;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author beou on 8/21/17 14:53
 */

public class MyPrincipal implements UserDetails {

    private static final long serialVersionUID = 1015478378277111822L;

    private Account account;
    private Long organizationId;
    private String organizationName;
    private Long accountId;
    private Set<Menu> menuSet;

    public MyPrincipal(Account account) {
        this.account = account;
        Organization organization = account.getOrganization();
        this.accountId = account.getId();
        this.organizationId = organization != null ? organization.getId() : null;
        this.organizationName = organization != null ? organization.getName() : null;

        menuSet = new HashSet<>();

        account.getPrivileges().forEach(x -> {
            menuSet.addAll(x.getMenuList());
        });
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Privilege> privileges = account.getPrivileges();
        return privileges.stream().map(x -> new SimpleGrantedAuthority(x.getName())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return account.getPassword();
    }

    @Override
    public String getUsername() {
        return account.getAccountId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;

    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return account.getStatus().equals(AccountStatus.ACTIVATED);
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Set<Menu> getMenuSet() {
        return menuSet;
    }

    public void setMenuSet(Set<Menu> menuSet) {
        this.menuSet = menuSet;
    }
}
