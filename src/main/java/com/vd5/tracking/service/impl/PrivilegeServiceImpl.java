package com.vd5.tracking.service.impl;

import com.vd5.tracking.entity.Privilege;
import com.vd5.tracking.repository.PrivilegeRepository;
import com.vd5.tracking.service.PrivilegeService;
import com.vd5.tracking.rest.request.PrivilegeRequest;
import com.vd5.tracking.utils.AuthenticationFacade;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author beou on 10/16/17 19:15
 */
@Service
public class PrivilegeServiceImpl implements PrivilegeService {
    private final PrivilegeRepository privilegeRepository;
    private final AuthenticationFacade authenticationFacade;

    public PrivilegeServiceImpl(PrivilegeRepository privilegeRepository, AuthenticationFacade authenticationFacade) {
        this.privilegeRepository = privilegeRepository;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public Page<Privilege> getAll(Specification<Privilege> specification, Pageable pageable) {
        return privilegeRepository.findAll(specification, pageable);
    }

    @Override
    public List<Privilege> getAll(Specification<Privilege> specification) {
        return privilegeRepository.findAll(specification);
    }

    @Override
    public Privilege getById(Long id) {
        return privilegeRepository.findOne(id);
    }

    @Override
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
    public void _delete(Long id) {
        privilegeRepository.delete(id);
    }
}
