package com.masschip.jh.controller;

import com.masschip.jh.dao.Userdao;
import com.masschip.jh.enties.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.List;

@RestController
public class tecontroller {

    @Autowired
    private Userdao userdao;
    @GetMapping("/g1")
    public List<User> getusers()
    {
        return  userdao.findAll();
    }



    @RequestMapping(value = "/g3",method = RequestMethod.POST)
    public boolean postusers(@RequestBody User user)
    {
        System.out.println("----------------->id="+
                user.getUserid()+"--->username="+user.getUsername());
        return  true;
    }

    @GetMapping("/g2")
    public List<User> getusersa()
    {
        return  userdao.findAll();
    }

}
