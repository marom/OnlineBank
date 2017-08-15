package com.userfront.service;

import com.userfront.domain.security.Role;

public interface RoleService {

    public Role findByName(String name);

}
