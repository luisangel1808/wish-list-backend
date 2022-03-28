package com.carvajal.whishlist.security.repository;


import com.carvajal.whishlist.security.entity.Role;
import com.carvajal.whishlist.security.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RepositoryRole extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleName roleName);
}
