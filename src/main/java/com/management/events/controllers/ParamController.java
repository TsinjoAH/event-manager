package com.management.events.controllers;

import com.management.events.exceptions.InputException;
import com.management.events.services.ParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static com.management.events.controllers.BaseController.render;

@Controller
public class ParamController {

    @Autowired
    ParameterService service;

    @GetMapping("/params")
    public ModelAndView setHomePageSize() {
        ModelAndView modelAndView = render("layout/layout", "params.jsp", "Parametre");
        modelAndView.addObject("currVal", service.getHomePageSize());
        return modelAndView;
    }

    @PostMapping("/params/save")
    public ModelAndView setHomePageSize(@RequestParam("value") Integer value) throws Exception {
        try {
            service.setHomePageSize(value);
        }
        catch (InputException exception) {
            ModelAndView model = setHomePageSize();
            model.addObject("error", exception.getMessage());
            return model;
        }
        return new ModelAndView("redirect:/params");
    }



}
