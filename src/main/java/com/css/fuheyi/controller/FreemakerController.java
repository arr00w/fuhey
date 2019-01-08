package com.css.fuheyi.controller;


import com.css.fuheyi.vo.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/freemarker")
public class FreemakerController {



    @RequestMapping("/showuser")
    public ModelAndView showUser(){
        ModelAndView modelAndView=new ModelAndView();
        User user=new User();
        user.setId("1");
        user.setName("付赫奕");
        modelAndView.addObject("user",user);
        modelAndView.setViewName("/demo/test");
        return modelAndView;
    }

    @RequestMapping("/jackson")
    public User showJackson(){
        RedirectView redirectView=new RedirectView();
        User user=new User();
        user.setId("1");
        user.setName("付赫奕");
        return user;
    }


    @RequestMapping("/showuserbee")
    public ModelAndView showUserBee(Model model){
        ModelAndView modelAndView=new ModelAndView();
        User user=new User();
        user.setId("1");
        user.setName("付赫奕");
        modelAndView.addObject("user",user);
        modelAndView.setViewName("/bee.btl");
        return modelAndView;
    }
}
