package ru.issuemanager.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.issuemanager.config.AuthenticationBean;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class AuthController {

    @GetMapping(path = "/login")
    public String login() {
        return "You are log in!";
    }
}
