package com.vd5.tracking.auth;

import com.vd5.tracking.entity.Account;
import com.vd5.tracking.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author beou on 8/21/17 14:44
 * @version 1.0
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Autowired
    public UserDetailsServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Account account = this.accountRepository.findByAccountId(userName);
        if (account == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new UserDetailsImpl(account);
    }
}
