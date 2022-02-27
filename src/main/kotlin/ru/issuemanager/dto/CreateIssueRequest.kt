package ru.issuemanager.dto

data class CreateIssueRequest(
    var groupId: Long = -1,
    var title: String = "",
    var description: String = "",
)
