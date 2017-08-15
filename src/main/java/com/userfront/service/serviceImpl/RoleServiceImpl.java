package com.userfront.service.serviceImpl;

import com.userfront.dao.RoleDao;
import com.userfront.domain.security.Role;
import com.userfront.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;

    @Override
    public Role findByName(String name) {
        return roleDao.findByName(name);
    }
}
