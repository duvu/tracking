package com.vd5.tracking.service.impl;

import com.vd5.tracking.entity.EventData;
import com.vd5.tracking.repository.EventDataRepository;
import com.vd5.tracking.service.EventDataService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author beou on 10/27/17 13:48
 */
@Service
public class EventDataServiceImpl implements EventDataService {

    private final EventDataRepository repository;

    public EventDataServiceImpl(EventDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public EventData create(EventData eventData) {
        return repository.save(eventData);
    }

    @Override
    public Page<EventData> getAll(Specification<EventData> specification, Pageable pageable) {
        return repository.findAll(pageable);
    }
}
