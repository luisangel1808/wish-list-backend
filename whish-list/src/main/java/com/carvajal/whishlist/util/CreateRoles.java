package com.carvajal.whishlist.util;

import com.carvajal.whishlist.security.entity.Role;
import com.carvajal.whishlist.security.enums.RoleName;
import com.carvajal.whishlist.security.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RoleService roleService;

    @Override
    public void run(String... args) throws Exception {
        Role roleAdmin = new Role(RoleName.ROLE_ADMIN);
        Role roleCustomer = new Role(RoleName.ROLE_CUSTOMER);
        roleService.save(roleAdmin);
        roleService.save(roleCustomer);
    }
}
