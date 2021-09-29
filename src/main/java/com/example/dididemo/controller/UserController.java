package com.example.dididemo.controller;

import com.example.dididemo.entity.User;
import com.example.dididemo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userService;
    @RequestMapping("/")
    public String index(){
        return  "index";
    }


    @RequestMapping(value = "/login")
    public String login(){
        return "/login.html";
    }
    @PostMapping(value = "/loginn")
    @ResponseBody
    public String login(String username,String password){
        System.out.println(username);
        User user=userService.findByNameAndPassword(username,password);

        if(user.getUsername()==null||user.getPassword()==null){
            return "error";
        }else {
            return "success";
        }
    }
    @RequestMapping(value = "/registry", method = RequestMethod.GET)
    public String start(){
        return "registry";
    }
    @PostMapping(value = "/registrys")
    @ResponseBody
    public String  register(String username,String password){
        System.out.println("duan dian 1");
        User user =userService.findByName(username);
        System.out.println(user.getUsername());
        if(user.getUsername() == null){
            //personService.register(id);
            userService.insertUser(username,password);
            return "Y";
        }
        return "N";
    }






















































//    public String toLogin(){
//        return "/login";
//    }

}
