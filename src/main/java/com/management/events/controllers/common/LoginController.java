package com.management.events.controllers.common;

import com.management.events.exceptions.InputException;
import com.management.events.models.common.LoginEntity;
import com.management.events.services.LoginService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginController<T extends LoginEntity, S extends LoginService<T>> {

    S service;
    final String key;
    final String successUrl;
    final String urlKey;

    public LoginController(S service, String key, String successUrl, String urlKey) {
        this.service = service;
        this.key = key;
        this.successUrl = successUrl;
        this.urlKey = urlKey;
    }

    @GetMapping("/")
    public String index(HttpServletRequest req) {
        return "redirect:/"+urlKey+"/login";
    }

    @GetMapping(value = "/login")
    public ModelAndView login() {
        ModelAndView view = new ModelAndView();
        view.setViewName("login");
        view.addObject("url", urlKey+"/do-login");
        return view;
    }

    @PostMapping("/do-login")
    public ModelAndView doLogin(T user, HttpSession session) {
        ModelAndView view = login();
        try {
            session.setAttribute(key, service.login(user));
            view.setViewName("redirect:" + successUrl);
            return view;
        } catch (InputException e) {
            view.addObject("inputError", e.getMessage());
        }
        return view;
    }

}
