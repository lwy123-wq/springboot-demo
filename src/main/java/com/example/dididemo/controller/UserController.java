package com.example.dididemo.controller;

import com.example.dididemo.entity.User;
import com.example.dididemo.service.UserServiceImpl;
import com.example.dididemo.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @RequestMapping(value = "/login")
    public String login() {
        return "/login.html";
    }

    @PostMapping(value = "/loginn")
    @ResponseBody
    public String login(HttpServletRequest request, HttpServletResponse response, String username, String password, Model model) {
        User user = userService.findByNameAndPassword(username, password);
        int expire = 60 * 60 * 24 * 7;  //表示7天
        CookieUtil.setCookie(request, response, "username", user.getUsername(), expire);
        CookieUtil.setCookie(request, response, "password", user.getPassword(), expire);
        System.out.println(user.getUsername());
        if (user.getUsername() == null || user.getPassword() == null) {
            return "error";
        } else {
            return "success";
        }
    }
    @RequestMapping("/index")
    public String mainPage(HttpServletRequest request, Model model){
        // 判断cookie是否存在，如存在则利用cookie登录，否则返回登录界面
        Map<String, String> map = CookieUtil.getCookies(request);
        String username = map.get("username");
        String password = map.get("password");
        System.out.println(username+password+"aaaa");
        if(username != null && password != null){
            User user = userService.findByName(username);
            if(username.equals(user.getUsername()) ){
                model.addAttribute("username", username);
                return "index";
            }
        }
        return "login";
    }

    @RequestMapping(value = "/registry", method = RequestMethod.GET)
    public String start() {
        return "registry";
    }

    @PostMapping(value = "/registrys")
    @ResponseBody
    public String register(String username, String password) {
        System.out.println("duan dian 1");
        User user = userService.findByName(username);
        System.out.println(user.getUsername());
        if (user.getUsername() == null) {
            //personService.register(id);
            userService.insertUser(username, password);
            return "Y";
        }
        return "N";
    }


    @ResponseBody
    @PostMapping("/uploadESignatureImg")
    public void uploadESignatureImg(MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
        /*String filePath = uploadESignature(file);

        return renderSuccess(filePath);*/
        System.out.println(file+"aaaaaaaaaaa");
    }
    @RequestMapping(value = "/picture", method = RequestMethod.GET)
    public String picture() {
        return "picture";
    }

    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    public String chat() {
        return "chat";
    }

    @RequestMapping("")
    @ResponseBody
    public String aa(@RequestParam("name") String name){
        return name;
    }
}





