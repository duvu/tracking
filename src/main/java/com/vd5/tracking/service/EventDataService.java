package com.vd5.tracking.service;

import com.vd5.tracking.entity.EventData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

/**
 * @author beou on 10/27/17 13:48
 */
public interface EventDataService {
    EventData create(EventData eventData);
    Page<EventData> getAll(Specification<EventData> specification, Pageable pageable);
}
