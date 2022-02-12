package ru.issuemanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class IssueManagerApplication

fun main(args: Array<String>) {
    runApplication<IssueManagerApplication>(*args)
}
