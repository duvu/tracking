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
    public Specification<Organization> searchAll(String search) {
        return null;
    }

    @Override
    public Specification<Organization> searchOrg(String search) {
        return null;
    }

    @Override
    public Specification<Organization> searchOne(String search) {
        return null;
    }

}
