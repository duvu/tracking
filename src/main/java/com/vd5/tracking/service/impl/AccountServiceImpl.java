package com.vd5.tracking.service.impl;

import com.vd5.tracking.entity.Account;
import com.vd5.tracking.repository.AccountRepository;
import com.vd5.tracking.service.AccountService;
import com.vd5.tracking.rest.request.AccountRequest;
import com.vd5.tracking.utils.AuthenticationFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author beou on 8/1/17 04:55
 * @version 1.0
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, PasswordEncoder passwordEncoder, AuthenticationFacade authenticationFacade) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public Page<Account> getAll(Specification<Account> specification, Pageable pageable) {
        return accountRepository.findAll(specification, pageable);
    }

    @Override
    public List<Account> getAll(Specification<Account> specification) {
        return accountRepository.findAll(specification);
    }

    @Override
    public Account getById(Long id) {
        Account account = accountRepository.findOne(id);
        if (account == null) {
            throw new NoSuchElementException("Account not found for id " + id);
        }
        return account;
    }

    @Override
    @Transactional
    public Account create(AccountRequest request) {
        Account account = Account.builder()
                .accountId(request.getAccountId())
                .build();

        if (!StringUtils.isEmpty(request.getPassword())) {
            account.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        return accountRepository.save(account);
    }

    @Override
    @Transactional
    public void update(Long id, AccountRequest request) {
        Account account = accountRepository.findOne(id);
        if (account == null) {
            throw new NoSuchElementException("Account not found for Id#" + id);
        }
        account.setAccountId(request.getAccountId());

        accountRepository.save(account);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        accountRepository.delete(id);
    }


    @Override
    public void addChild(Long parentId, Account childAccount) {

    }

    @Override
    public void removeChild(Long parenId, Long childId) {

    }
}