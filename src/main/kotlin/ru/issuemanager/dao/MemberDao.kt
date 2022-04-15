package ru.issuemanager.dao

import mu.KotlinLogging
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.issuemanager.dto.AddMemberRequest

private val logger = KotlinLogging.logger {}

@Repository
class MemberDao(
    private val jdbcTemplate: JdbcTemplate
) {
    fun insertMember(request: AddMemberRequest) = try {
        val sql = "insert into members( " +
                " group_id, user_id) " +
                " VALUES (${request.groupId}, ${request.userId});"
        jdbcTemplate.execute(sql)
    } catch (e: Exception) {
        logger.error { e.message }
        null
    }

    fun deleteMember(groupId: Long, userId: Long) = try {
        val scriptGetMemberId = "select member_id from members" +
                " where user_id = $userId and group_id = $groupId"
        val memberId = jdbcTemplate.queryForObject(scriptGetMemberId, Long::class.java)
        val returnIssues = "update issues set member_id = null, status = 'ToDo' " +
                " where member_id = $memberId"
        jdbcTemplate.update(returnIssues)
        val deleteMember = "delete from members " +
                " where group_id = $groupId and user_id = $userId"
        jdbcTemplate.execute(deleteMember)
    } catch (e: Exception) {
        logger.error { e.message }
        null
    }

}