package com.masschip.jh.dao;

import com.masschip.jh.enties.Menu;
import com.masschip.jh.enties.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface Menudao
        extends JpaRepository<Menu,String> {

    public Optional<Menu> findBymenuid(String menuid);

}
