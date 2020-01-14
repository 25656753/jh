package com.masschip.jh.controller;

import com.masschip.jh.security.testlombok;
import com.masschip.jh.dao.Roledao;
import com.masschip.jh.dao.Userdao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class logincontrol {

    @Autowired
    private Userdao userdao;

    @Autowired
    private Roledao roledao;

    @GetMapping("/")
    public String home(ModelMap map)
    {
        testlombok tt=testlombok.of(32);
        map.put("tt", 8548);
         System.out.println("------>"+userdao.count());
        return "index";
    }
}
