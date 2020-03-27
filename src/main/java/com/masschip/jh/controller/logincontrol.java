package com.masschip.jh.controller;

import com.masschip.jh.dao.Roledao;
import com.masschip.jh.dao.Userdao;
import com.masschip.jh.enties.Role;
import com.masschip.jh.service.Roleservice;
import com.masschip.jh.utils.MessageUtils;
import com.masschip.jh.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

//https://blog.csdn.net/weixin_41722928/article/details/102817307
//https://blog.csdn.net/nb7474/article/details/88696205
@Controller
@Slf4j
public class logincontrol {

    @Autowired
    private Userdao userdao;
    @Autowired
    private MessageUtils messageUtils;
    @Autowired
    private Roledao roledao;
    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private Roleservice roleservice;

private Logger logger= LoggerFactory.getLogger(getClass());
    @GetMapping("/")
    public String home(ModelMap map) {
        List<Role> roles = roleservice.getallroles();
System.out.println("count====="+redisUtil.keys("*").size());
        map.put("tt", 8548);
        map.put("vv", roles);
        Role f=new Role();
        f.setRolename("dsds");
        f.setPs("dasdad");

        List<Role>  pp=new ArrayList<>();
        pp.add(f);
        String bb=f.getRolename();
        map.put("bb",bb);
        map.put("ppsd",pp);
        redisUtil.set("role", f);
        redisUtil.lSet("gg", pp);
       map.put("role", (Role)redisUtil.get("role"));
        System.out.println("---redis"+redisUtil.lGet("gg", 1, 1));
         logger.debug("----------aaaaaa333fdfddf");

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
