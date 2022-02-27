package ru.issuemanager.dto

data class AssignIssueRequest(
    var userId: Long = -1,
    var issueId: Long = -1
)
