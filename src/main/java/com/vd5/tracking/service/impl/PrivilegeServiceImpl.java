package com.vd5.tracking.service.impl;

import com.vd5.tracking.entity.Privilege;
import com.vd5.tracking.repository.PrivilegeRepository;
import com.vd5.tracking.service.PrivilegeService;
import com.vd5.tracking.web.request.PrivilegeRequest;
import com.vd5.tracking.utils.AuthenticationFacade;
import com.vd5.tracking.web.specification.PrivilegeSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author beou on 10/16/17 19:15
 */

@Slf4j
@Service
public class PrivilegeServiceImpl implements PrivilegeService {

    private PrivilegeRepository privilegeRepository;
    private final PrivilegeSpecification privilegeSpecification;
    private AuthenticationFacade authenticationFacade;

    @Autowired
    public PrivilegeServiceImpl(PrivilegeRepository privilegeRepository,
                                PrivilegeSpecification privilegeSpecification,
                                AuthenticationFacade authenticationFacade) {
        this.privilegeRepository = privilegeRepository;
        this.privilegeSpecification = privilegeSpecification;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public Page<Privilege> getAll(String search, Pageable pageable) {
        Specification<Privilege> specification = privilegeSpecification.search(search);
        return privilegeRepository.findAll(specification, pageable);
    }

    @Override
    public List<Privilege> getAll(String search) {
        Specification<Privilege> specification = privilegeSpecification.search(search);
        return privilegeRepository.findAll(specification);
    }

    @Override
    public Privilege getById(Long id) {
        return privilegeRepository.findOne(id);
    }

    @Override
    @Transactional
    public Privilege create(PrivilegeRequest request) {
        Privilege privilege = Privilege.builder()
                .name(request.getName())
                .description(request.getDescription())
                .createdBy(authenticationFacade.getCurrentUserName())
                .build();
        return privilegeRepository.save(privilege);
    }

    @Override
    public void update(Long id, PrivilegeRequest request) {
        Privilege privilege = privilegeRepository.getOne(id);
        if (privilege != null) {
            privilege.setName(request.getName());
            privilege.setDescription(request.getDescription());
            privilege.setUpdatedBy(authenticationFacade.getCurrentUserName());
            privilegeRepository.save(privilege);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void delete(Long id) {
        privilegeRepository.delete(id);
    }
}
