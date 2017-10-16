package com.vd5.tracking.repository;

import com.vd5.tracking.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author beou on 10/16/17 19:23
 */
public interface PrivilegeRepository extends BaseRepository<Privilege> {

    Privilege findByName(String name);
}
