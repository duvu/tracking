package com.vd5.tracking.web.specification;

import com.vd5.tracking.entity.Organization_;
import com.vd5.tracking.entity.Privilege;
import com.vd5.tracking.entity.Privilege_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

/**
 * @author beou on 10/23/17 12:42
 */
@Component
public class PrivilegeSpecification extends AbstractSpecification<Privilege> {
    @Override
    public Specification<Privilege> search(String search) {
        return (root, query, cb) -> cb.or(cb.like(root.get(Privilege_.name), getSearchTerm(search)),
                cb.like(root.get(Privilege_.description), getSearchTerm(search)));
    }
}
