package com.userfront;

import com.userfront.dao.RoleDao;
import com.userfront.domain.security.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private RoleDao roleDao;

    public DataLoader(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public void run(String... args) throws Exception {

        loadRoles();
    }

    private void loadRoles() {

        final Role admin = new Role();
        admin.setRoleId(0);
        admin.setName("ROLE_ADMIN");

        final Role user = new Role();
        user.setRoleId(1);
        user.setName("ROLE_USER");

        roleDao.save(admin);
        roleDao.save(user);
    }
}
