package com.vd5.tracking.web.specification;

import com.vd5.tracking.entity.Account;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * @author beou on 10/6/17 16:01
 * @version 1.0
 */
public class AccountSpecification {
    public static Specification<Account> searchName(String name) {
        return new Specification<Account>() {
            @Override
            public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("accountId"), name);
            }
        };
    }
}
