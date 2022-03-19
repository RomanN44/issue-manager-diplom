package ru.issuemanager.dto

data class AddMemberRequest(
    var userId: Long = 0,
    var groupId: Long = 0
)
