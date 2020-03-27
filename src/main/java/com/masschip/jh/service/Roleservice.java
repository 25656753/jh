package com.masschip.jh.service;

import com.masschip.jh.dao.Roledao;
import com.masschip.jh.enties.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Roleservice {

    @Autowired
    private Roledao roledao;

    @Cacheable("allroles")
    public List<Role> getallroles()
    {
        System.out.println("----------->getallroles");
        return roledao.findAll();
    }


}
