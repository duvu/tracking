package com.vd5.tracking.auth;

import com.vd5.tracking.entity.Account;
import com.vd5.tracking.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @author beou on 8/21/17 14:44
 * */

@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final AccountRepository accountRepository;

    @Autowired
    public UserDetailsServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        log.info("user_name" + userName);

        Account account = this.accountRepository.findAccountByAccountId(userName);
        if (account == null) {
            throw new UsernameNotFoundException("Username not found");
        }
        return new MyUserDetailsImpl(account);
    }
}
