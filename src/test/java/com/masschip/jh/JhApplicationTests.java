package com.masschip.jh;

import com.masschip.jh.dao.RoleRepository;
import com.masschip.jh.dao.UserRepository;
import com.masschip.jh.enties.Role;
import com.masschip.jh.enties.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class JhApplicationTests {
    @Autowired
    private UserRepository userdao;

    @Autowired
    private RoleRepository roledao;

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
    Role t=    roledao.findById("402883346f9e406c016f9e40a6050000").get();
    System.out.println(t.getUsers().size()+"=====");
    }
}
