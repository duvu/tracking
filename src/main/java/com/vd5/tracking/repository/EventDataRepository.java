package com.vd5.tracking.repository;

import com.vd5.tracking.entity.EventData;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @author beou on 10/27/17 13:47
 */
@Repository
public interface EventDataRepository extends ElasticsearchRepository<EventData, Long> {
}
