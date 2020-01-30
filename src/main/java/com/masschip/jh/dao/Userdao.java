package com.masschip.jh.dao;

import com.masschip.jh.enties.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Userdao extends JpaRepository<User,String> {
    public Optional<User> findByUsername(String username);
}
