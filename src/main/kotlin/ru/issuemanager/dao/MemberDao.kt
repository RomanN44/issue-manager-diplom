package ru.issuemanager.dao

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.issuemanager.dto.AddMemberRequest

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
        null
    }

    fun deleteMember(groupId: Long, userId: Long) = try {
        val sql = "delete from members " +
                " where group_id = $groupId and user_id = $userId;"
        jdbcTemplate.execute(sql)
    } catch (e: Exception) {
        null
    }
}