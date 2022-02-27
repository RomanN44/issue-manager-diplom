package ru.issuemanager.dto

data class ChangeIssueStatusRequest(
    var issueId: Long = -1,
    var status: String = ""
)
