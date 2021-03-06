package com.vd5.tracking.service;

import com.vd5.tracking.entity.Privilege;
import com.vd5.tracking.web.request.PrivilegeRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;


/**
 * @author beou on 10/16/17 18:36
 */
public interface PrivilegeService extends BaseService<Privilege, PrivilegeRequest>{
}
