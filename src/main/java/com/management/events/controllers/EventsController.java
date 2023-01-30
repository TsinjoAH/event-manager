package com.management.events.controllers;

import com.management.events.exceptions.FormattedError;
import com.management.events.exceptions.InputException;
import com.management.events.models.Event;
import com.management.events.models.formdata.EventFilter;
import com.management.events.services.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class EventsController {

    EventService service;

    public EventsController(EventService service) {
        this.service = service;
    }

    @PostMapping("/save-event")
    public ResponseEntity<?> saveEvent(@RequestBody Event event) throws Exception{
        try {
            return ResponseEntity.ok(service.create(event));
        }
        catch (InputException e) {
            return ResponseEntity.badRequest().body(new FormattedError(e));
        }
    }

    @GetMapping("/list-event")
    public ModelAndView eventList(EventFilter filter, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("layout");
        if (session.getAttribute("connected") == null) {
            modelAndView.setViewName("redirect:admin/login");
            return modelAndView;
        }
        return list(modelAndView, filter);
    }

    @GetMapping("/front-office")
    public ModelAndView frontOfficeList(EventFilter filter) {
        ModelAndView modelAndView = new ModelAndView("layout-front");
        return list(modelAndView, filter);
    }


    private ModelAndView list (ModelAndView modelAndView, EventFilter filter) {
        modelAndView.addObject("mainPage", "list-event.jsp");
        modelAndView.addObject("pageTitle", "Liste");
        modelAndView.addObject("formData", service.fetchFormData());
        modelAndView.addObject("events", service.findAll(filter));
        modelAndView.addObject("filter", filter);
        return modelAndView;
    }

    @GetMapping("/add-event")
    public ModelAndView eventForm(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("layout");
        if (session.getAttribute("connected") == null) {
            modelAndView.setViewName("redirect:admin/login");
            return modelAndView;
        }
        modelAndView.addObject("mainPage", "add-event.jsp");
        modelAndView.addObject("pageTitle", "Ajout");
        modelAndView.addObject("formData", service.fetchFormData());
        return modelAndView;
    }


}
