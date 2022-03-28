package com.carvajal.whishlist.security.service;

import com.carvajal.whishlist.security.entity.Role;
import com.carvajal.whishlist.security.enums.RoleName;
import com.carvajal.whishlist.security.repository.RepositoryRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class RoleService {

    @Autowired
    RepositoryRole repositoryRole;

    public Optional<Role> findByRoleName(RoleName roleName){
        return repositoryRole.findByRoleName(roleName);
    }
    public void save(Role role){
        repositoryRole.save(role);
    }
}