package com.vd5.tracking.service.impl;

import com.vd5.tracking.entity.Organization;
import com.vd5.tracking.repository.OrganizationRepository;
import com.vd5.tracking.service.OrganizationService;
import com.vd5.tracking.utils.AuthenticationFacade;
import com.vd5.tracking.rest.request.OrganizationRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author beou on 10/16/17 20:03
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final AuthenticationFacade authenticationFacade;

    public OrganizationServiceImpl(OrganizationRepository organizationRepository, AuthenticationFacade authenticationFacade) {
        this.organizationRepository = organizationRepository;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public Page<Organization> getAll(Specification<Organization> specification, Pageable pageable) {
        return organizationRepository.findAll(specification, pageable);
    }

    @Override
    public List<Organization> getAll(Specification<Organization> specification) {
        return organizationRepository.findAll(specification);
    }

    @Override
    public Organization getById(Long id) {
        return organizationRepository.findOne(id);
    }

    @Override
    @Transactional
    public Organization create(OrganizationRequest request) {
        Organization organization = Organization.builder()
                .name(request.getName())
                .emailAddress(request.getEmailAddress())
                .phoneNumber(request.getPhoneNumber())
                .addressLine1(request.getAddressLine1())
                .addressLine2(request.getAddressLine2())
                .createdBy(authenticationFacade.getCurrentUserName())
                .build();
        return organizationRepository.save(organization);
    }

    @Override
    @Transactional
    public void update(Long id, OrganizationRequest request) {
        Organization organization = organizationRepository.findOne(id);
        if (organization != null) {
            organization.setName(request.getName());
            organization.setEmailAddress(request.getEmailAddress());
            organization.setAddressLine1(request.getAddressLine1());
            organization.setPhoneNumber(request.getPhoneNumber());
            organization.setAddressLine2(request.getAddressLine2());
            organization.setUpdatedBy(authenticationFacade.getCurrentUserName());
            organizationRepository.save(organization);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    @Transactional
    public void delete(Long id) {
        organizationRepository.delete(id);
    }
}
