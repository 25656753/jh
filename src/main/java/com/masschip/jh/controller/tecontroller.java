package com.masschip.jh.controller;

import com.masschip.jh.dao.Userdao;
import com.masschip.jh.enties.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/g2")
    public List<User> getusersa()
    {
        return  userdao.findAll();
    }
}
