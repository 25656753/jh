package com.masschip.jh.controller;

import com.masschip.jh.dao.Roledao;
import com.masschip.jh.dao.Userdao;
import com.masschip.jh.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//https://blog.csdn.net/weixin_41722928/article/details/102817307
//https://blog.csdn.net/nb7474/article/details/88696205
@Controller
public class logincontrol {

    @Autowired
    private Userdao userdao;
    @Autowired
    private MessageUtils messageUtils;
    @Autowired
    private Roledao roledao;

    @GetMapping("/")
    public String home(ModelMap map) {
        map.put("tt", 8548);
        System.out.println("------>" + messageUtils.get("welcome"));
        System.out.println("------>" + userdao.count());
        System.out.println("---------->aaaaa" + SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "index";
    }

    @RequestMapping("/login")
    public String login() {
        return "login/login";
    }


    @GetMapping("/Access_Denied")
    public String AccessDenied(String username, String password) {
        System.out.println("Access_Denied------>" + username + "---" + password);
        return "login/accessdenied";
    }


    @GetMapping("/logout")
    public String loginout() {
        SecurityContextHolder.clearContext();
        return "redirect:/login";
    }


    @GetMapping("/tt")
    // @RolesAllowed("USER")
    public String tta() {
        return "tt";
    }

    @ResponseBody
    @GetMapping("/whoim")
    public String tt() {
        Authentication au = SecurityContextHolder.getContext().getAuthentication();

        System.out.println("token-----" + SecurityContextHolder.getContext().getAuthentication().getDetails().toString());
        return "aaaa";
    }


}
