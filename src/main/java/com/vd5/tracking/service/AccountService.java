package com.vd5.tracking.service;

import com.vd5.tracking.entity.Account;
import com.vd5.tracking.entity.Status;
import com.vd5.tracking.model.Response;
import com.vd5.tracking.web.request.AccountRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author beou on 8/1/17 03:33
 * @version 1.0
 */

public interface AccountService {
    // 1. search & sort & pagination
    // 2. get all & pagination
    // 3. get specific
    // 4. create (return CREATED)
    // 5. update
    // 6. delete

    public Page<Account> searchAndSort(Specification specification, Pageable pageable);

    public Account getById(Long id);
    public Response getByAccountId(String accountId);
    public Response getByEmail(String email);
    public Response getByPhone(String phone);

    /**
     * @param id the id of account
     * @return all account under the account, includes the account itself
     * */
    public Response getAllById(Long id);

    /**
     * @param accountId the accountId of account
     * @return all account under the account, includes the account itself
     * */
    public Response getAllByAccountId(String accountId);

    public Account add(AccountRequest accountRequest);
    public Response addChild(Long parentId, Account childAccount);
    public Response removeChild(Long parenId, Long childId);
    public Response update(Long id, Account account);
    public Response delete(Long id);
}
