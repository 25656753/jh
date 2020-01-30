package com.masschip.jh.dao;

import com.masschip.jh.enties.Permission;
import com.masschip.jh.enties.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Permissiondao
        extends JpaRepository<Permission,String> {
}
