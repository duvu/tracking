package com.vd5.tracking.service;

import com.vd5.tracking.entity.Account;
import com.vd5.tracking.web.request.AccountRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

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
    public List<Account> searchAndSort(Specification specification);
    public Account getById(Long id);

    public Account create(AccountRequest request);
    public Account update(Long id, AccountRequest request);
    public void delete(Long id);

    public void addChild(Long parentId, Account childAccount);
    public void removeChild(Long parenId, Long childId);

}
