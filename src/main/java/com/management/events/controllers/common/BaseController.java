package com.management.events.controllers.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {

    @GetMapping("/")
    public String index() {
        return "redirect:front-office";
    }

    public static ModelAndView render(String layout, String page, String pageTitle) {
        ModelAndView modelAndView = new ModelAndView(layout);
        modelAndView.addObject("mainPage", "../"+page);
        modelAndView.addObject("pageTitle", pageTitle);
        return modelAndView;
    }

}
