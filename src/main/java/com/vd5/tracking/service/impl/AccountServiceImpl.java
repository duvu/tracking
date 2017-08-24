package com.vd5.tracking.service.impl;

import com.vd5.tracking.entity.Account;
import com.vd5.tracking.model.Response;
import com.vd5.tracking.model.ResultCode;
import com.vd5.tracking.repository.AccountRepository;
import com.vd5.tracking.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author beou on 8/1/17 04:55
 * @version 1.0
 */
@Slf4j
@Service
public class AccountServiceImpl implements AccountService{

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Response getById(Long id) {
        log.info("...getById");
        try {
            if (id == null || id < 0) {
                return new Response(ResultCode.ERROR, null, "Invalid request");
            } else {
                Account account = accountRepository.findOne(id);
                return new Response(ResultCode.SUCCESS, account, "Success");
            }
        } catch (Exception ex) {
            log.error(ExceptionUtils.getStackTrace(ex));
            return new Response(ResultCode.SUCCESS, null, "Internal Error");
        } finally {
            log.info("___getById");
        }
    }

    @Override
    public Response getByAccountId(String accountId) {
        return null;
    }

    @Override
    public Response getByEmail(String email) {
        return null;
    }

    @Override
    public Response getByPhone(String phone) {
        return null;
    }

    /**
     * @param id the id of account
     * @return all account under the account, includes the account itself
     */
    @Override
    public Response getAllById(Long id) {
        return null;
    }

    /**
     * @param accountId the accountId of account
     * @return all account under the account, includes the account itself
     */
    @Override
    public Response getAllByAccountId(String accountId) {
        return null;
    }

    @Override
    public Response add(Account account) {
        log.info("...add account");
        try {
            Account createdAccount = accountRepository.save(account);
            return new Response(ResultCode.SUCCESS, createdAccount, "created account");
        } catch (Exception ex) {
            log.error(ExceptionUtils.getStackTrace(ex));
            return new Response(ResultCode.ERROR, null, "Internal Error");
        } finally {
            log.info("___add account");
        }
    }

    @Override
    public Response addChild(Long parentId, Account childAccount) {
        return null;
    }

    @Override
    public Response removeChild(Long parenId, Long childId) {
        return null;
    }

    @Override
    public Response update(Long id, Account account) {
        return null;
    }

    @Override
    public Response delete(Long id) {
        return null;
    }
}
