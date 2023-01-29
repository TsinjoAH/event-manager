package com.management.events.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EventsController {

    @GetMapping("/list-event")
    public String eventList() {
        return "layout";
    }

}
