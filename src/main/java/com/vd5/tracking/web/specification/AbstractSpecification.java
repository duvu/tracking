package com.vd5.tracking.web.specification;

import com.vd5.tracking.entity.Account;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author beou on 10/6/17 16:01
 * @version 1.0
 */
public abstract class AbstractSpecification<T> {
    public Specification<T> queryAnd(Map<String, String> params) {
        return (root, query, cb) -> {
            List<Predicate> l = params.entrySet().stream().map(x -> cb.like(root.get(x.getKey()), x.getValue())).collect(Collectors.toList());
            if (l != null && l.size() > 0) {
                Predicate p0 = l.get(0);
                for (int i = 0; i < l.size(); i++) {
                    p0 = cb.and(p0,l.get(i));
                }
                return p0;
            }
            return null;
        };
    }

    public Specification<T> queryOr(Map<String, String> params) {
        return (root, query, cb) -> {
            List<Predicate> l = params.entrySet().stream().map(x -> cb.like(root.get(x.getKey()), x.getValue())).collect(Collectors.toList());
            if (l != null && l.size() > 0) {
                Predicate p0 = l.get(0);
                for (int i = 0; i < l.size(); i++) {
                    p0 = cb.or(p0,l.get(i));
                }
                return p0;
            }
            return null;
        };
    }
}
