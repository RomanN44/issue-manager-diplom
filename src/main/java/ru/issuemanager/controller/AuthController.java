package ru.issuemanager.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.issuemanager.config.AuthenticationBean;


@RestController
@CrossOrigin(origins = "*")
public class AuthController {

    @GetMapping(path = "/auth")
    public String login() {
        return "You are log in!";
    }

}
