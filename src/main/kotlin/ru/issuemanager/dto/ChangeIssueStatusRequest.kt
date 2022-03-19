package ru.issuemanager.dto

data class ChangeIssueStatusRequest(
    var issueId: Long = 0,
    var status: String = ""
)
