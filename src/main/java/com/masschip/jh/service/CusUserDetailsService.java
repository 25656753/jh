package com.masschip.jh.service;

import com.masschip.jh.dao.Userdao;
import com.masschip.jh.enties.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CusUserDetailsService
        implements UserDetailsService {
    @Autowired
    private Userdao userdao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

       Optional<User> data=  userdao.findByUsername(username);
        return  data.orElse(null);
    }
}
