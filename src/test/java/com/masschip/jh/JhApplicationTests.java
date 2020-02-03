package com.masschip.jh;

import com.masschip.jh.dao.Menudao;
import com.masschip.jh.dao.Roledao;
import com.masschip.jh.dao.Userdao;
import com.masschip.jh.enties.Menu;
import com.masschip.jh.enties.Permission;
import com.masschip.jh.enties.Role;
import com.masschip.jh.enties.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.AntPathMatcher;

import java.util.*;

@SpringBootTest
class JhApplicationTests {
    @Autowired
    private Userdao userdao;

    @Autowired
    private Roledao roledao;

    @Test
    void contextLoads() {
    }

    @Test
    void Testdb() {
        User user = new User();
        user.setNickname("jamhe");
        user.setUsername("jamhe");
        //   user.setPs("ps");
        user.setCreate_by(user);
        user.setCreate_time(new Date());
        Role role = new Role();
        role.setRolename("业务组");
        role.setCreate_by(user);
        role.setCreate_time(new Date());


        userdao.save(user);
        roledao.save(role);
        Set<Role> rolesaa = new HashSet<>();
        rolesaa.add(role);

        Role rolea = new Role();
        rolea.setRolename("研发组");
        rolea.setCreate_by(user);
        rolea.setCreate_time(new Date());
        roledao.save(rolea);

        rolesaa.add(rolea);
        user.setRoles(rolesaa);
        userdao.save(user);
    }

    @Test
    void t2() {
    Role t=    roledao.findById("402883346f9e406c016f9e40a6da0001").get();
    System.out.println(t.getUsers().size()+"=====");
    }



    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Test
    void t4()
    {
      System.out.println("password========"+passwordEncoder.encode("123456"));

      if  (passwordEncoder.matches("1234567","$2a$10$ggetqngmAtvkW2e3.NnyG.YZcRVkNAhXiUQQPUUOcCecNDh/nUME.")) {
          System.out.println("password ok------");
      }
    }


    @Test
    void antPathMatchert5()
    {
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        if (antPathMatcher.match("/aa/bb/**", "/aa/bb/edit"))
            System.out.println("-------->ok");
    }
   @Autowired
private Menudao menudao;
    @Test
    void t6()
    {
       Optional<Menu> menu= menudao.findBymenuid("1");
    for(Permission i: menu.orElse(new Menu()).getPermissionSet())
    {
        System.out.println("------------permisson------->"+i.getPermissname());
    }
    }



    }


