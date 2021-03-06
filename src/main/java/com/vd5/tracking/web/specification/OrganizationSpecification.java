package com.vd5.tracking.web.specification;

import com.vd5.tracking.entity.Organization;
import com.vd5.tracking.entity.Organization_;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

/**
 * @author beou on 10/16/17 23:17
 */

@Slf4j
@Component
public class OrganizationSpecification extends AbstractSpecification<Organization> {
    @Override
    public Specification<Organization> search(String search) {
        return (root, query, cb) -> cb.or(cb.like(root.get(Organization_.name), getSearchTerm(search)),
                cb.like(root.get(Organization_.emailAddress), getSearchTerm(search)));
    }
}
