package com.masschip.jh.controller;

import com.masschip.jh.enties.User;
import com.masschip.jh.service.CusUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/xinhai")
public class UserController {

    @Autowired
    private CusUserDetailsService userservice;

    @GetMapping("/")
    public String xinhairoot() {
        return "xinhairoot";
    }

    //@ModelAttribute("user")
    void getmodel(User user,ModelMap map)
    {
        if (user.getUserid()!=null)
        {
           Optional<User> userres=userservice.getuserbyid(user.getUserid());
           if (userres.isPresent()) {
           System.out.println("---------user"+user.getUsername());
               map.put("user", userres.get());

           }
        }

    }


    @GetMapping("/userlist")
    public String getlist(ModelMap map) {
        List<User> data = userservice.getalluser();
        map.put("data", data);
        return "user/userlist";
    }


    @RequestMapping(value = {"addnew","useredit", "useredit/{id}"},
    method = {RequestMethod.POST,RequestMethod.GET})
    public String useredit(
             @Valid @ModelAttribute("data") User user
            , BindingResult result
            ,@PathVariable(value = "id", required = false) String userid
            , HttpServletRequest request
            ,ModelMap map) {
        User  cuser=new User();

       // HttpServletRequest a;

        if ("GET".equalsIgnoreCase(request.getMethod())) {
            if (userid == null)   //新增
            {

            } else  //修改
            {
             cuser=userservice.getuserbyid(userid).get();
            }
          map.put("data", cuser);
            map.put("aa","123");
            map.put("allFeatures",new Boolean[]{true,false,true});
            return "user/useredit";
        } else    //保存user对象
            {

                if (result.hasErrors())
                {
                    map.put("data", user);
                    return "user/useredit";
                }
           // System.out.println(user.getEmail()+"---"+user.getUsername());
           //     System.out.println("ggg"+result.hasErrors());
                return "redirect:/xinhai/userlist";
        }


    }


}
