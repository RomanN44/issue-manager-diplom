package ru.issuemanager.dto

data class AssignIssueRequest(
    var userId: Long = 0,
    var issueId: Long = 0,
    var groupId: Long = 0
)
