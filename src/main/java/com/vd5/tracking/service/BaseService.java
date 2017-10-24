package com.vd5.tracking.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * @author beou on 10/16/17 23:31
 */
public interface BaseService<T, R> {
    Page<T> getAll(String search, Pageable pageable);
    List<T> getAll(String search);
    T getById(Long id);
    T create(R request);
    void update(Long id, R request);
    void delete(Long id);
}
