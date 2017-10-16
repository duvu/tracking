package com.vd5.tracking.auth;

import com.vd5.tracking.entity.Account;
import com.vd5.tracking.entity.Privilege;
import com.vd5.tracking.model.AccountStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author beou on 8/21/17 14:53
 */

public class MyUserDetailsImpl implements UserDetails {

    private static final long serialVersionUID = 1015478378277111822L;
    private Account account;

    public MyUserDetailsImpl(Account account) {
        this.account = account;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //need to return authorities here from account
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
        return account.getStatus().ordinal() >= AccountStatus.ACTIVATED.ordinal();
    }
}
