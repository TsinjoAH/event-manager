package com.management.events.controllers;

import com.management.events.exceptions.InputException;
import com.management.events.models.Admin;
import com.management.events.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @GetMapping("/")
    public String index(HttpServletRequest req) {
        return "redirect:admin/login";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    @PostMapping("/do-login")
    public ModelAndView doLogin (Admin admin, HttpSession session) {
        ModelAndView view = new ModelAndView();
        try {
            session.setAttribute("connected", adminService.login(admin));
            view.setViewName("redirect:/list-event");
            return view;
        }
        catch (InputException e) {
            view.addObject("inputError", e.getMessage());
        }
        view.setViewName("login");
        return view;
    }


}
