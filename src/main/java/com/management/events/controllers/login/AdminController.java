package com.management.events.controllers.login;

import com.management.events.controllers.common.LoginController;
import com.management.events.models.Admin;
import com.management.events.services.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController extends LoginController<Admin, AdminService> {

    public AdminController(AdminService service) {
        super(service, "connected", "/admin/list-event", "admin");
    }

}
