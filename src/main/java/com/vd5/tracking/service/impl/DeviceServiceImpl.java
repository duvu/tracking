package com.vd5.tracking.service.impl;

import com.vd5.tracking.entity.Device;
import com.vd5.tracking.repository.AccountRepository;
import com.vd5.tracking.repository.DeviceRepository;
import com.vd5.tracking.service.DeviceService;
import com.vd5.tracking.utils.AuthenticationFacade;
import com.vd5.tracking.web.request.DeviceRequest;
import com.vd5.tracking.web.specification.DeviceSpecification;
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
 * @author beou on 8/1/17 03:33
 */

@Slf4j
@Service
public class DeviceServiceImpl implements DeviceService {

    private final DeviceRepository deviceRepository;
    private final AccountRepository accountRepository;
    private final DeviceSpecification deviceSpecification;
    private final AuthenticationFacade authenticationFacade;


    @Autowired
    public DeviceServiceImpl(DeviceRepository deviceRepository,
                             AccountRepository accountRepository,
                             DeviceSpecification deviceSpecification, AuthenticationFacade authenticationFacade) {
        this.deviceRepository = deviceRepository;
        this.accountRepository = accountRepository;
        this.deviceSpecification = deviceSpecification;
        this.authenticationFacade = authenticationFacade;
    }

    @Override
    public Page<Device> getAll(String search, Pageable pageable) {
        Specification<Device> specification = deviceSpecification.search(search);
        return deviceRepository.findAll(specification, pageable);
    }

    @Override
    public List<Device> getAll(String search) {
        Specification<Device> specification = deviceSpecification.search(search);
        return deviceRepository.findAll(specification);
    }

    @Override
    public Device getById(Long id) {
        return deviceRepository.findOne(id);
    }

    @Override
    @Transactional
    public Device create(DeviceRequest request) {
        //todo update vehicle informations

        log.info("create#deviceId: " + request.getDeviceId());
        log.info("create#accountId: " + request.getAccountId());

        Long usageId = request.getAccountId() != null ? request.getAccountId() : authenticationFacade.getAccountId();

        Device device = Device.builder()
                .name(request.getName())
                .deviceId(request.getDeviceId())
                .account(accountRepository.findOne(usageId))
                .protocol(request.getProtocol())
                .serialNumber(request.getSerivalNumber())
                .modelName(request.getModelName())
                .manufacturerName(request.getManufacturerName())
                .firmwareVerison(request.getFirmwareVerison())
                .originalCountry(request.getOriginalCountry())
                .createdBy(authenticationFacade.getCurrentUserName())
                .build();

        return deviceRepository.save(device);
    }

    @Override
    @Transactional
    public void update(Long id, DeviceRequest request) {
        Device device = deviceRepository.findOne(id);
        if (device != null) {
            device.setName(request.getName());
            device.setDeviceId(request.getDeviceId());
            device.setAccount(accountRepository.findOne(request.getAccountId()));
            device.setProtocol(request.getProtocol());
            device.setSerialNumber(request.getSerivalNumber());
            device.setModelName(request.getModelName());
            device.setManufacturerName(request.getManufacturerName());
            device.setFirmwareVerison(request.getFirmwareVerison());
            device.setOriginalCountry(request.getOriginalCountry());
            device.setUpdatedBy(authenticationFacade.getCurrentUserName());

            deviceRepository.save(device);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void delete(Long id) {
        Device device = deviceRepository.findOne(id);
        if (device != null) {
            deviceRepository.delete(id);
        } else {
            throw new NoSuchElementException();
        }
    }
}
