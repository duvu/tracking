package com.vd5.tracking.web.specification;

import com.vd5.tracking.entity.Device;
import com.vd5.tracking.entity.Device_;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.Predicate;

/**
 * @author beou on 10/23/17 16:12
 */

@Component
public class DeviceSpecification extends AbstractSpecification<Device> {

    @Override
    public Specification<Device> search(String search) {
        return (root, query, cb) -> {
            Predicate predicate = cb.or(cb.like(root.get(Device_.deviceId), getSearchTerm(search)), cb.like(root.get(Device_.name), getSearchTerm(search)));

            return predicate;
        };
    }
}
