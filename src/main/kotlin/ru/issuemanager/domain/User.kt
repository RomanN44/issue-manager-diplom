package ru.issuemanager.domain

data class User(
    val userId: Long = 0,
    val username: String = "",
    val email: String? = null,
    val fullName: String? = null
)
