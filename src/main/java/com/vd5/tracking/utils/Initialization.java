package com.vd5.tracking.utils;

import com.vd5.tracking.entity.Account;
import com.vd5.tracking.entity.Organization;
import com.vd5.tracking.entity.Privilege;
import com.vd5.tracking.model.AccountStatus;
import com.vd5.tracking.repository.AccountRepository;
import com.vd5.tracking.repository.OrganizationRepository;
import com.vd5.tracking.repository.PrivilegeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;

/**
 * @author beou on 10/16/17 19:30
 */
@Component
@Slf4j
/**
 * TODO: move init data to config file
 * */
public class Initialization {
    //to setup init data
    private final AccountRepository accountRepository;
    private final OrganizationRepository organizationRepository;
    private final PrivilegeRepository privilegeRepository;

    private final PasswordEncoder passwordEncoder;

    public Initialization(AccountRepository accountRepository, OrganizationRepository organizationRepository,
                          PrivilegeRepository privilegeRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.organizationRepository = organizationRepository;
        this.privilegeRepository = privilegeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        log.info("#Start setting up system ...");
        initPrivileges();
        initOrganizations();
        initAccounts();
    }

    private void initPrivileges() {
        Privilege privilege0 = privilegeRepository.findByName(RolesList.ROLE_SYSTEM_ADMIN);
        Privilege privilege1 = privilegeRepository.findByName(RolesList.ROLE_ADMINISTRATOR);
        Privilege privilege2 = privilegeRepository.findByName(RolesList.ROLE_MODERATOR);
        Privilege privilege3 = privilegeRepository.findByName(RolesList.ROLE_USER);
        if (privilege0 == null) {
            privilege0 = Privilege.builder().name(RolesList.ROLE_SYSTEM_ADMIN).description("System Admin Group!").createdBy("admin@vd5.com").build();
            privilegeRepository.save(privilege0);
        }
        if (privilege1 == null) {
            privilege1 = Privilege.builder().name(RolesList.ROLE_ADMINISTRATOR).description("Company Admin Group!").createdBy("admin@vd5.com").build();
            privilegeRepository.save(privilege1);
        }
        if (privilege2 == null) {
            privilege2 = Privilege.builder().name(RolesList.ROLE_MODERATOR).description("Company Mod Group!").createdBy("admin@vd5.com").build();
            privilegeRepository.save(privilege2);
        }
        if (privilege3 == null) {
            privilege3 = Privilege.builder().name(RolesList.ROLE_USER).description("User Groups!").createdBy("admin@vd5.com").build();
            privilegeRepository.save(privilege3);
        }
    }

    private void initOrganizations() {
        Organization organization = organizationRepository.findByName("VD5.COM");
        if (organization == null) {
            organization = Organization.builder()
                    .name("VD5.COM")
                    .phoneNumber("(+84) 9 3458 0599")
                    .emailAddress("admin@vd5.com")
                    .addressLine1("2201 CT2C NGHIA DO, 106 HQV STR")
                    .addressLine2("HANOI CITY, VIETNAM")
                    .createdBy("admin@vd5.com")
                    .build();
            organizationRepository.save(organization);
        }
    }

    private void initAccounts() {
        Account account = accountRepository.findAccountByAccountId("sysadmin");
        if (account == null) {
            account = Account.builder()
                    .accountId("sysadmin")
                    .password(passwordEncoder.encode("123456"))
                    .emailAddress("admin@vd5.com")
                    .phoneNumber("(+84) 934580599")
                    .firstName("Vu")
                    .lastName("Du")
                    .organization(organizationRepository.findByName("VD5.COM"))
                    .privileges(new HashSet<Privilege>(Arrays.asList(privilegeRepository.findByName("ROLE_SYSTEM_ADMIN"))))
                    .status(AccountStatus.ACTIVATED)
                    .build();

            accountRepository.save(account);
        }
    }
}
