package ru.issuemanager.dto

data class RegistrationUserRequest(
    val username: String = "",
    val password: String = "",
    val email: String = "",
    val fullName: String = ""
)
