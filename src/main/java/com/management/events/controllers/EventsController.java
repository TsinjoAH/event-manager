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

@Controller
public class EventsController {

    EventService service;

    public EventsController(EventService service) {
        this.service = service;
    }

    @GetMapping("/list-event")
    public ModelAndView eventList(EventFilter filter) {
        ModelAndView modelAndView = new ModelAndView("layout");
        modelAndView.addObject("mainPage", "list-event.jsp");
        modelAndView.addObject("pageTitle", "Liste");
        modelAndView.addObject("formData", service.fetchFormData());
        modelAndView.addObject("events", service.findAll(filter));
        modelAndView.addObject("filter", filter);
        return modelAndView;
    }

    @GetMapping("/add-event")
    public ModelAndView eventForm() {
        ModelAndView modelAndView = new ModelAndView("layout");
        modelAndView.addObject("mainPage", "add-event.jsp");
        modelAndView.addObject("pageTitle", "Ajout");
        modelAndView.addObject("formData", service.fetchFormData());
        return modelAndView;
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

}
