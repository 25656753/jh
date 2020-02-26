package com.masschip.jh.controller;

import com.masschip.jh.enties.User;
import com.masschip.jh.service.CusUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/xinhai")
public class UserController {

    @Autowired
    private CusUserDetailsService userservice;

    @GetMapping("/")
    public String xinhairoot()
    {
        return "xinhairoot";
    }

    @GetMapping("/userlist")
    public String getlist(ModelMap map)
    {
        List<User> data= userservice.getalluser();
         map.put("data",data);

        return "user/userlist";
    }

}
