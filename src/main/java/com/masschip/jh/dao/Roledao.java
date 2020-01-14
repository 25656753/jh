package com.masschip.jh.dao;

import com.masschip.jh.enties.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Roledao
        extends JpaRepository<Role,String> {
}
