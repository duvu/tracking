package com.vd5.tracking.repository;

import com.vd5.tracking.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author beou on 10/16/17 19:46
 */
public interface OrganizationRepository extends BaseRepository<Organization> {

    Organization findByName(String name);
}
