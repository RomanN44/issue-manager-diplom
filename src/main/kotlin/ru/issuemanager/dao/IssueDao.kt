package ru.issuemanager.dao

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.issuemanager.dto.AssignIssueRequest
import ru.issuemanager.dto.ChangeIssueStatusRequest
import ru.issuemanager.dto.CreateIssueRequest

@Repository
class IssueDao(
    private val jdbcTemplate: JdbcTemplate
) {
    fun selectFromIssueByUser(id: Long) = try {
        val sql = "select * from issues where member_id = $id"
        jdbcTemplate.queryForList(sql)
    } catch (e: Exception) {
        null
    }

    fun selectFromIssueByGroup(id: Long) = try {
        val sql = "select * from issues " +
                " where group_id = $id"
        jdbcTemplate.queryForList(sql)
    } catch (e: Exception) {
        null
    }

    fun updateIssueStatus(request: ChangeIssueStatusRequest) = try {
        val sql = "update issues set status = '${request.status}'" +
                " where issue_id =${request.issueId}"
        jdbcTemplate.update(sql)
    } catch (e: Exception) {
        null
    }

    fun insertIssue(request: CreateIssueRequest) = try {
        val sql = "insert into public.issues(" +
                " member_id, title, description, status, date_start, date_finish, group_id)" +
                " values (null, '${request.title}'," +
                " '${request.description}', 'ToDo', now(), null, ${request.groupId});"
        jdbcTemplate.execute(sql)
    } catch (e: Exception) {
        null
    }

    fun updateIssueUser(request: AssignIssueRequest) = try {
        val sql = "update issues set member_id = '${request.userId}'" +
                " where issue_id =${request.issueId}"
        jdbcTemplate.update(sql)
    } catch (e: Exception) {
        null
    }

}