package com.management.events.controllers;

import com.management.events.exceptions.FormattedError;
import com.management.events.exceptions.InputException;
import com.management.events.models.Author;
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

import static com.management.events.controllers.common.BaseController.render;

@Controller
public class EventsController {

    EventService service;

    public EventsController(EventService service) {
        this.service = service;
    }

    @PostMapping("/save-event")
    public ResponseEntity<?> saveEvent(@RequestBody Event event, HttpSession session) throws Exception{
        try {
            Author author = (Author) session.getAttribute("author_connected");
            if (author == null) {
                return ResponseEntity.badRequest().body(new FormattedError("Vous devez être connecté pour créer un événement"));
            }
            event.setAuthor(author);
            return ResponseEntity.ok(service.create(event));
        }
        catch (InputException e) {
            return ResponseEntity.badRequest().body(new FormattedError(e));
        }
    }

    // controller method to get pending events of current author in the session
    @GetMapping("/my-events")
    public ModelAndView myEvents(EventFilter filter, HttpSession session) {
        ModelAndView modelAndView = render("layout/author-layout", "list-event.jsp", "Mes événements");
        if (session.getAttribute("author_connected") == null) {
            modelAndView.setViewName("redirect:author/login");
            return modelAndView;
        }
        Author author = (Author) session.getAttribute("author_connected");
        modelAndView.addObject("formData", service.fetchFormData());
        modelAndView.addObject("events", service.getPendingEventsByAuthor(filter,author.getId()));
        modelAndView.addObject("filter", filter);
        return modelAndView;
    }


    @GetMapping("/list-event")
    public ModelAndView eventList(EventFilter filter, HttpSession session) {
        ModelAndView modelAndView = render("layout/author-layout", "list-event.jsp", "Liste");
        if (session.getAttribute("author_connected") == null) {
            modelAndView.setViewName("redirect:author/login");
            return modelAndView;
        }
        modelAndView.addObject("formData", service.fetchFormData());
        modelAndView.addObject("events", service.getValidatedEvents(filter));
        modelAndView.addObject("filter", filter);
        return modelAndView;
    }

    @GetMapping("/front-office")
    public ModelAndView frontOfficeList(EventFilter filter) {
        ModelAndView view = render("layout/layout-front", "list-event.jsp", "Front office");
        view.addObject("formData", service.fetchFormData());
        view.addObject("events", service.getValidatedEvents(filter));
        view.addObject("filter", filter);
        return view;
    }

    @GetMapping("/add-event")
    public ModelAndView eventForm(HttpSession session) {
        ModelAndView modelAndView = render("layout/author-layout", "add-event.jsp", "Ajout");
        if (session.getAttribute("author_connected") == null) {
            modelAndView.setViewName("redirect:author/login");
            return modelAndView;
        }
        modelAndView.addObject("formData", service.fetchFormData());
        return modelAndView;
    }

}
