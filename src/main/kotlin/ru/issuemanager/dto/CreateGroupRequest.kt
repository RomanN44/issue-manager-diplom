package ru.issuemanager.dto

data class CreateGroupRequest(
    var title: String = "",
    var description: String? = null,
    var userId: Long = 0
)
