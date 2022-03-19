package ru.issuemanager.dto

data class CreateIssueRequest(
    var groupId: Long = 0,
    var title: String = "",
    var description: String = "",
)
