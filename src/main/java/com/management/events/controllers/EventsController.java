package com.management.events.controllers;

import com.management.events.exceptions.FormattedError;
import com.management.events.exceptions.InputException;
import com.management.events.models.Author;
import com.management.events.models.Event;
import com.management.events.models.formdata.EventFilter;
import com.management.events.services.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import static com.management.events.controllers.BaseController.render;

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
            modelAndView.setViewName("redirect:/author/login");
            return modelAndView;
        }
        Author author = (Author) session.getAttribute("author_connected");
        modelAndView.addObject("formData", service.fetchFormData());
        modelAndView.addObject("events", service.getPendingEventsByAuthor(filter,author.getId()));
        modelAndView.addObject("filter", filter);
        return modelAndView;
    }

    @GetMapping("/author/list-event")
    public ModelAndView eventList(EventFilter filter, HttpSession session) {
        return eventList(filter, session, "layout/author-layout", "author_connected", "/author/login");
    }

    // pending events for admin
    @GetMapping("/admin/pending-events")
    public ModelAndView pendingEvents(EventFilter filter, HttpSession session) {
        ModelAndView modelAndView = render("layout/layout", "list-event.jsp", "Evénements en attente");
        if (session.getAttribute("connected") == null) {
            modelAndView.setViewName("redirect:/admin/login");
            return modelAndView;
        }
        modelAndView.addObject("formData", service.fetchFormData());
        modelAndView.addObject("events", service.getPendingEvents(filter));
        modelAndView.addObject("filter", filter);
        modelAndView.addObject("allowedValidation", true);
        return modelAndView;
    }

    @GetMapping("/admin/list-event")
    public ModelAndView adminEventList(EventFilter filter, HttpSession session) {
        return eventList(filter, session, "layout/layout", "connected", "/admin/login");
    }

    // eventList method with layout, session key and loginUrl as parameters
    private ModelAndView eventList(EventFilter filter, HttpSession session, String layout, String sessionKey, String loginUrl) {
        ModelAndView modelAndView = render(layout, "list-event.jsp", "Liste");
        if (session.getAttribute(sessionKey) == null) {
            modelAndView.setViewName("redirect:"+loginUrl);
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
            modelAndView.setViewName("redirect:/author/login");
            return modelAndView;
        }
        modelAndView.addObject("formData", service.fetchFormData());
        return modelAndView;
    }

    // validate event via get method, with id as parameter
    @PostMapping("/admin/events/validate/{id}")
    public ModelAndView publish(HttpSession session, @PathVariable int id) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/list-event");
        if (session.getAttribute("connected") == null) {
            modelAndView.setViewName("redirect:/admin/login");
            return modelAndView;
        }
        service.publishEvent(id);
        return modelAndView;
    }

    @PostMapping("/admin/events/update-date/{id}")
    public ModelAndView updateDate(HttpSession session, @PathVariable int id, Event event) {
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/list-event");
        if (session.getAttribute("connected") == null) {
            modelAndView.setViewName("redirect:/admin/login");
            return modelAndView;
        }
        service.publishAt(id, event.getPublishedDate());
        return modelAndView;
    }


}
