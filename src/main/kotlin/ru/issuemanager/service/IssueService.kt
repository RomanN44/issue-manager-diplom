package ru.issuemanager.service

import mu.KotlinLogging
import org.springframework.stereotype.Service
import ru.issuemanager.dao.IssueDao
import ru.issuemanager.dto.AssignIssueRequest
import ru.issuemanager.dto.ChangeIssueStatusRequest
import ru.issuemanager.dto.CreateIssueRequest

private val logger = KotlinLogging.logger {}

@Service
class IssueService(
    private val issueDao: IssueDao
) {
    fun getIssuesByUser(userId: Long) = try {
        issueDao.selectFromIssueByUser(userId)
    } catch (e: Exception) {
        logger.error { e.message }
        null
    }

    fun getIssueByGroup(groupId: Long) = try {
        issueDao.selectFromIssueByGroup(groupId)
    } catch (e: Exception) {
        logger.error { e.message }
        null
    }

    fun changeStatusIssue(request: ChangeIssueStatusRequest)  = try {
        issueDao.updateIssueStatus(request)
    } catch (e: Exception) {
        logger.error { e.message }
        null
    }

    fun createIssue(request: CreateIssueRequest)  = try {
        issueDao.insertIssue(request)
    } catch (e: Exception) {
        logger.error { e.message }
        null
    }

    fun assignIssue(request: AssignIssueRequest)  = try {
        issueDao.updateIssueUser(request)
    } catch (e: Exception) {
        logger.error { e.message }
        null
    }
}