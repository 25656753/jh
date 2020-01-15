package com.masschip.jh.service;

import com.masschip.jh.dao.Userdao;
import com.masschip.jh.enties.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CusUserDetailsService
        implements UserDetailsService {
    @Autowired
    private Userdao userdao;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<User> data=  userdao.findByUsername(username);
        if (data.size()>0)
        return data.get(0);
        else
            return null;
    }
}
