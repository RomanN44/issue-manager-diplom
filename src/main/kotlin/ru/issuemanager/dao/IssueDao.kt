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
        val sql = "select issue_id, issues.title, issues.description, status, date_start, date_finish, groups.title as groupTitle" +
                " from public.issues join public.groups on issues.group_id = groups.group_id " +
                " where member_id = $id"
        jdbcTemplate.queryForList(sql)
    } catch (e: Exception) {
        null
    }

    fun selectFromIssueByGroup(id: Long) = try {
        val sql = "select issue_id, issues.member_id, title, description, status, date_start, " +
                " date_finish, users.full_name, users.email " +
                " from issues left join members on issues.member_id = members.member_id " +
                " left join users on members.user_id = users.user_id " +
                " where issues.group_id = $id"
        jdbcTemplate.queryForList(sql)
    } catch (e: Exception) {
        null
    }

    fun updateIssueStatus(request: ChangeIssueStatusRequest) = try {
        val sql = "update issues set status = '${request.status}'" +
                " where issue_id =${request.issueId}"
        val res = jdbcTemplate.update(sql)
        if(request.status == "Done") {
            updateIssueDateFinish(request.issueId)
        }
        res
    } catch (e: Exception) {
        null
    }

    fun insertIssue(request: CreateIssueRequest) = try {
        val sql = "insert into issues(" +
                " member_id, title, description, status, date_start, date_finish, group_id)" +
                " values (null, '${request.title}'," +
                " '${request.description}', 'ToDo', now(), null, ${request.groupId});"
        jdbcTemplate.execute(sql)
    } catch (e: Exception) {
        null
    }

    fun updateIssueUser(request: AssignIssueRequest) = try {
        val scriptGetMemberId = "select member_id from members" +
                " where user_id = ${request.userId} and group_id = ${request.groupId}"
        val memberId = jdbcTemplate.queryForObject(scriptGetMemberId, Long::class.java)
        val sql = "update issues set member_id = $memberId" +
                " where issue_id =${request.issueId}"
        jdbcTemplate.update(sql)
    } catch (e: Exception) {
        null
    }

    private fun updateIssueDateFinish(issueId: Long) = try {
        val sql = "update issues set date_finish = now()" +
                " where issue_id = $issueId"
        jdbcTemplate.update(sql)
    } catch (e: Exception) {
        null
    }
}