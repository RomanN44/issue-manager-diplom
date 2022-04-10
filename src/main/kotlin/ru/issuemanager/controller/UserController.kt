package ru.issuemanager.controller

import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import ru.issuemanager.dto.RegistrationUserRequest
import ru.issuemanager.service.UserService
import org.springframework.security.core.context.SecurityContextHolder

private val logger = KotlinLogging.logger {}

@RestController
@CrossOrigin(origins = ["*"])
class UserController(
    private val userService: UserService
) {
    @PostMapping("/user/registration")
    fun registration(@RequestBody request: RegistrationUserRequest) = try {
        userService.registration(request)
    } catch (e: Exception) {
        logger.error { e.message }
        ResponseEntity<String>(HttpStatus.BAD_REQUEST)
    }

    //+
    @GetMapping("/user/auth")
    fun auth() = try {
        val auth: Authentication = SecurityContextHolder.getContext().authentication
        userService.getIdByName(auth.name)
    } catch (e: Exception) {
        logger.error { e.message }
        ResponseEntity<String>(HttpStatus.BAD_REQUEST)
    }

    //+
    @GetMapping("/user/getInfo/{id}")
    fun getInfo(@PathVariable id: Long) = try {
        userService.getInfo(id)
    } catch (e: Exception) {
        logger.error { e.message }
        ResponseEntity<String>(HttpStatus.BAD_REQUEST)
    }
}