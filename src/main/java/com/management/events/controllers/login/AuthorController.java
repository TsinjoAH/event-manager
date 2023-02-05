package com.management.events.controllers.login;

import com.management.events.controllers.common.LoginController;
import com.management.events.models.Author;
import com.management.events.services.AuthorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/author")
public class AuthorController extends LoginController<Author, AuthorService> {

    public AuthorController(AuthorService service) {
        super(service, "author_connected", "/author/list-event", "author");
    }

}
