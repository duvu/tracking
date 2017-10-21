package com.vd5.tracking.web;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;

import java.util.List;

/**
 * @author beou on 10/16/17 22:49
 */

public interface BaseController<I, O> {
    Page<O> getAll(String search, Pageable pageable);
    List<O> getAll(String search);
    O getById(Long id);
    O create(I request, BindingResult result);
    void update(Long id, I request, BindingResult result);
    void delete(Long id);
}
