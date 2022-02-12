package ru.issuemanager.controller

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["*"])
class AuthController {

    @GetMapping(path = ["/auth"])
    fun login() = "You are log in!"
}
