package com.vd5.tracking.service;

import com.vd5.tracking.entity.Account;
import com.vd5.tracking.rest.request.AccountRequest;

/**
 * @author beou on 8/1/17 03:33
 * @version 1.0
 */

public interface AccountService extends BaseService<Account, AccountRequest> {
    void addChild(Long parentId, Account childAccount);
    void removeChild(Long parenId, Long childId);

}
