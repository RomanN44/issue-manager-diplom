package ru.issuemanager.controller

import mu.KotlinLogging
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.issuemanager.dto.*
import ru.issuemanager.service.IssueService

private val logger = KotlinLogging.logger {}

@RestController
@CrossOrigin(origins = ["*"])
class IssueController(
    private val issueService: IssueService
) {
    //+
    @GetMapping("/issue/get/byUser/{userId}")
    fun getIssuesByUser(@PathVariable userId: Long) = try {
        issueService.getIssuesByUser(userId)
    } catch (e: Exception) {
        logger.error { e.message }
        ResponseEntity<String>(HttpStatus.BAD_REQUEST)
    }

    @GetMapping("/issue/get/byGroup/{groupId}")
    fun getIssuesByGroup(@PathVariable groupId: Long) = try {
        issueService.getIssueByGroup(groupId)
    } catch (e: Exception) {
        logger.error { e.message }
        ResponseEntity<String>(HttpStatus.BAD_REQUEST)
    }

    @PostMapping("/issue/changeStatus")
    fun changeStatusIssue(@RequestBody request: ChangeIssueStatusRequest) = try {
        issueService.changeStatusIssue(request)
        ResponseEntity<String>(HttpStatus.OK)
    } catch (e: Exception) {
        logger.error { e.message }
        ResponseEntity<String>(HttpStatus.BAD_REQUEST)
    }

    @PostMapping("/issue/create")
    fun createIssue(@RequestBody request: CreateIssueRequest) = try {
        issueService.createIssue(request)
        ResponseEntity<String>(HttpStatus.OK)
    } catch (e: Exception) {
        logger.error { e.message }
        ResponseEntity<String>(HttpStatus.BAD_REQUEST)
    }

    @PostMapping("/issue/assignIssue")
    fun assignIssue(@RequestBody request: AssignIssueRequest) = try {
        issueService.assignIssue(request)
        ResponseEntity<String>(HttpStatus.OK)
    } catch (e: Exception) {
        logger.error { e.message }
        ResponseEntity<String>(HttpStatus.BAD_REQUEST)
    }
}