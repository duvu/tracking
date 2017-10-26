package com.vd5.tracking.repository;

import com.vd5.tracking.entity.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author beou on 10/25/17 13:30
 */

@Repository
public interface MenuRepository extends CrudRepository<Menu, Long> {
    Menu findByName(String name);
}
