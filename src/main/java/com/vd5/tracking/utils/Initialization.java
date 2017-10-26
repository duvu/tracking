package com.vd5.tracking.utils;

import com.vd5.tracking.entity.Account;
import com.vd5.tracking.entity.Menu;
import com.vd5.tracking.entity.Organization;
import com.vd5.tracking.entity.Privilege;
import com.vd5.tracking.model.AccountStatus;
import com.vd5.tracking.repository.AccountRepository;
import com.vd5.tracking.repository.MenuRepository;
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
    private final MenuRepository menuRepository;

    private final PasswordEncoder passwordEncoder;

    public Initialization(AccountRepository accountRepository, OrganizationRepository organizationRepository,
                          PrivilegeRepository privilegeRepository, MenuRepository menuRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.organizationRepository = organizationRepository;
        this.privilegeRepository = privilegeRepository;
        this.menuRepository = menuRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {



        log.info("#Start setting up system ...");
        initMenus();
        initPrivileges();
        initOrganizations();
        initAccounts();
    }

    private void initMenus() {
        Menu menuDashboard = menuRepository.findByName("dashboard");
        Menu menuReport = menuRepository.findByName("report");

        Menu menuAdmin = menuRepository.findByName("admin");
        Menu submenuAccount = menuRepository.findByName("account");
        Menu submenuDevice = menuRepository.findByName("device");

        Menu menuSysAdmin = menuRepository.findByName("sysadmin");
        Menu submenuMenu = menuRepository.findByName("menu");
        Menu submenuPrivilege = menuRepository.findByName("privilege");
        Menu submenuOrganizatuib = menuRepository.findByName("organization");



        if (menuDashboard == null) {
            menuDashboard = Menu.builder().name("dashboard").text("Dashboard").ordered(1).matIcon("map").build();
            menuRepository.save(menuDashboard);
        }
        if (menuReport == null) {
            menuReport = Menu.builder().name("report").text("Report").ordered(2).matIcon("report").build();
            menuRepository.save(menuReport);
        }
        if (menuAdmin == null) {
            menuAdmin = Menu.builder().name("admin").text("Administration").ordered(3).matIcon("settings").build();
            menuRepository.save(menuAdmin);
        }

        if (submenuAccount == null) {
            submenuAccount = Menu.builder()
                    .name("account")
                    .text("Account Administration")
                    .ordered(7)
                    .routeLink("/main/_admin/_account")
                    .parent(menuAdmin)
                    .matIcon("sub-menu")
                    .build();
            menuRepository.save(submenuAccount);
        }

        if (submenuDevice == null) {
            submenuDevice = Menu.builder()
                    .name("device")
                    .text("Device Administration")
                    .ordered(7)
                    .routeLink("/main/_admin/_device")
                    .parent(menuAdmin)
                    .matIcon("sub-menu")
                    .build();
            menuRepository.save(submenuDevice);
        }

        if (menuSysAdmin == null) {
            menuSysAdmin = Menu.builder().name("sysadmin").text("System Admin").ordered(4).matIcon("settings").build();
            menuRepository.save(menuSysAdmin);
        }

        if (submenuMenu == null) {
            submenuMenu = Menu.builder()
                    .name("menu")
                    .text("Menu")
                    .ordered(5)
                    .routeLink("/main/sys/menu")
                    .parent(menuSysAdmin)
                    .matIcon("sub-menu")
                    .build();
            menuRepository.save(submenuMenu);
        }

        if (submenuPrivilege == null) {
            submenuPrivilege = Menu.builder()
                    .name("privilege")
                    .text("Privilege")
                    .ordered(6)
                    .routeLink("/main/sys/privilege")
                    .parent(menuSysAdmin)
                    .matIcon("sub-menu")
                    .build();
            menuRepository.save(submenuPrivilege);
        }

        if (submenuOrganizatuib == null) {
            submenuOrganizatuib = Menu.builder()
                    .name("organization")
                    .text("Organization")
                    .ordered(7)
                    .routeLink("/main/sys/organization")
                    .parent(menuSysAdmin)
                    .matIcon("sub-menu")
                    .build();
            menuRepository.save(submenuOrganizatuib);
        }
    }

    private void initPrivileges() {
        Privilege privilege0 = privilegeRepository.findByName(RolesList.ROLE_SYSTEM_ADMIN);
        Privilege privilege1 = privilegeRepository.findByName(RolesList.ROLE_ADMINISTRATOR);
        Privilege privilege2 = privilegeRepository.findByName(RolesList.ROLE_MODERATOR);
        Privilege privilege3 = privilegeRepository.findByName(RolesList.ROLE_USER);

        Menu menuDashboard = menuRepository.findByName("dashboard");
        Menu menuReport = menuRepository.findByName("report");
        Menu menuAdmin = menuRepository.findByName("admin");
        Menu menuSysAdmin = menuRepository.findByName("sysadmin");

        if (privilege0 == null) {
            privilege0 = Privilege.builder()
                    .name(RolesList.ROLE_SYSTEM_ADMIN)
                    .description("System Admin Group!")
                    .createdBy("admin@vd5.com")
                    .menuList(Arrays.asList(menuDashboard, menuReport, menuAdmin, menuSysAdmin))
                    .build();
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
