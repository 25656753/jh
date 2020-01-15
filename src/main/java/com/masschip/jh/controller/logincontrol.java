package com.masschip.jh.controller;

import com.masschip.jh.dao.Roledao;
import com.masschip.jh.dao.Userdao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//https://blog.csdn.net/weixin_41722928/article/details/102817307
//https://blog.csdn.net/nb7474/article/details/88696205
@Controller
public class logincontrol {

    @Autowired
    private Userdao userdao;

    @Autowired
    private Roledao roledao;

    @GetMapping("/")
    public String home(ModelMap map) {
        map.put("tt", 8548);
        System.out.println("------>" + userdao.count());
        System.out.println("---------->aaaaa" + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    @PostMapping("/login/form")
    public void logina(String username, String password) {
        System.out.println("/login/form------>" + username + "---" + password);


    }


    @GetMapping("/logout")
    public String loginout() {
        return "login/login";
    }

    @ResponseBody
    @GetMapping("/whoim")
    public String tt() {
        return "aaaa";
    }

}
