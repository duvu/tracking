package com.vd5.tracking.service.impl;

import com.vd5.tracking.entity.Account;
import com.vd5.tracking.entity.Privilege;
import com.vd5.tracking.model.AccountStatus;
import com.vd5.tracking.repository.AccountRepository;
import com.vd5.tracking.repository.OrganizationRepository;
import com.vd5.tracking.repository.PrivilegeRepository;
import com.vd5.tracking.service.AccountService;
import com.vd5.tracking.web.request.AccountRequest;
import com.vd5.tracking.utils.AuthenticationFacade;
import com.vd5.tracking.web.specification.AccountSpecificationHelper;
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
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author beou on 8/1/17 04:55
 * @version 1.0
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    private final OrganizationRepository organizationRepository;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final PrivilegeRepository privilegeRepository;
    private final AccountSpecificationHelper specificationHelper;

    private final AuthenticationFacade authenticationFacade;

    @Autowired
    public AccountServiceImpl(OrganizationRepository organizationRepository, AccountRepository accountRepository,
                              PasswordEncoder passwordEncoder, PrivilegeRepository privilegeRepository,
                              AccountSpecificationHelper specificationHelper, AuthenticationFacade authenticationFacade) {
        this.organizationRepository = organizationRepository;
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.privilegeRepository = privilegeRepository;
        this.specificationHelper = specificationHelper;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public Page<Account> getAll(String search, Pageable pageable) {
        Specification<Account> specification = specificationHelper.search(search);
        return accountRepository.findAll(specification, pageable);
    }

    @Override
    public List<Account> getAll(String search) {
        Specification<Account> specification = specificationHelper.search(search);
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
        Set<Long> privilegeIds = request.getPrivilegeIds();
        Set<Privilege> privilegeSet = null;
        if (privilegeIds != null) {
            privilegeSet = privilegeIds.stream().map(privilegeRepository::findOne).collect(Collectors.toSet());
        }

        Account account = Account.builder()
                .accountId(request.getAccountId())
                .password(passwordEncoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .status(AccountStatus.ACTIVATED)
                .organization(organizationRepository.findOne(authenticationFacade.getOrganizationId()))
                .privileges(privilegeSet)
                .phoneNumber(request.getPhoneNumber())
                .photoUrl(request.getPhotoUrl())
                .addressLine1(request.getAddressLine1())
                .addressLine2(request.getAddressLine2())
                .emailAddress(request.getEmailAddress())
                .createdBy(authenticationFacade.getCurrentUserName())
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
        account.setFirstName(request.getFirstName());
        account.setLastName(request.getLastName());
        account.setPhoneNumber(request.getPhoneNumber());
        account.setPhotoUrl(request.getPhotoUrl());
        account.setAddressLine1(request.getAddressLine1());
        account.setAddressLine2(request.getAddressLine2());
        account.setEmailAddress(request.getEmailAddress());
        account.setUpdatedBy(authenticationFacade.getCurrentUserName());

        accountRepository.save(account);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        accountRepository.delete(id);
    }
}