package com.vd5.tracking.web.specification;

import com.vd5.tracking.entity.Account;
import com.vd5.tracking.entity.Account_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author beou on 10/7/17 01:02
 * @version 1.0
 */
@Component
public class AccountSpecification extends AbstractSpecification<Account> {

    @Override
    public Specification<Account> search(String search) {
        return (root, query, cb) -> cb.or(cb.like(root.get(Account_.accountId), search),
                cb.like(root.get(Account_.firstName), search));
    }

}
