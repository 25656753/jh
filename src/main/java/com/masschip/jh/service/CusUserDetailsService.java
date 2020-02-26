package com.masschip.jh.service;

import com.masschip.jh.dao.Roledao;
import com.masschip.jh.dao.Userdao;
import com.masschip.jh.enties.Permission;
import com.masschip.jh.enties.Role;
import com.masschip.jh.enties.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    /**
     * 得到指定用户的所有权限，包括用户权限和所属role组带的权限
     * @param user 查询的user
     * @return  具有的权限集
     */
    public Set<Permission> getallpermissions(User user)
    {
        Set<Permission> permissionSet=new HashSet<>();
        permissionSet.addAll(user.getPermissionSet());

        for (Role role:user.getRoles())
        {
            permissionSet.addAll(role.getPermissionSet());
        }

        return  permissionSet;
    }

    /**
     *
     * @return 全部用户
     */
    public  List<User> getalluser()
    {
        return userdao.findAll();
    }


}
