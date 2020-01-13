package com.masschip.jh.controller;

import com.masschip.jh.security.testlombok;
import com.masschip.jh.dao.RoleRepository;
import com.masschip.jh.dao.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class logincontrol {

    @Autowired
    private UserRepository userdao;

    @Autowired
    private RoleRepository roledao;

    @GetMapping("/")
    public String home(ModelMap map)
    {
        testlombok tt=testlombok.of(32);
        map.put("tt", 8548);
         System.out.println("------>"+userdao.count());
        return "index";
    }
}
