package ru.issuemanager.domain

data class Group (
    val group_id: Long = 0,
    val user_id: Long = 0,
    val title: String? = null,
    val description: String? = null
)