package com.vd5.tracking.web.specification;

import com.vd5.tracking.entity.Account;
import com.vd5.tracking.entity.Account_;
import com.vd5.tracking.entity.Organization_;
import com.vd5.tracking.utils.AuthenticationFacade;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;

/**
 * @author beou on 10/7/17 01:02
 * @version 1.0
 */
@Component
public class AccountSpecificationHelper extends AbstractSpecification<Account> {

    private final AuthenticationFacade authenticationFacade;

    public AccountSpecificationHelper(AuthenticationFacade authenticationFacade) {
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public Specification<Account> search(String search) {
        return (root, query, cb) -> {
            Predicate predicate = cb.or(cb.like(root.get(Account_.accountId), getSearchTerm(search)),
                    cb.like(root.get(Account_.firstName), getSearchTerm(search)),
                    cb.like(root.get(Account_.lastName), getSearchTerm(search)));

            if (authenticationFacade.isSysAdmin()) {
                return predicate;
            } else if (authenticationFacade.isAdmin() || authenticationFacade.isModerator()) {
                return cb.and(cb.equal(root.join(Account_.organization).get(Organization_.id), authenticationFacade.getOrganizationId()), predicate);
            } else {
                return cb.and(cb.equal(root.join(Account_.organization).get(Organization_.id), authenticationFacade.getOrganizationId()),
                        cb.equal(root.get(Account_.id), authenticationFacade.getAccountId()), predicate);
            }
        };
    }
}
