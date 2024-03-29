package ru.issuemanager.dao

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import ru.issuemanager.dto.CreateGroupRequest

@Repository
class GroupDao(
    private val jdbcTemplate: JdbcTemplate
) {
    fun insertGroup(request: CreateGroupRequest) = try {
        val insertGroup = "insert into groups(" +
                " user_id, title, description)" +
                " values (${request.userId}, '${request.title}','${request.description}');"
        jdbcTemplate.execute(insertGroup)
        val selectIdNewGroup = "select group_id from groups " +
                " where user_id = ${request.userId} and " +
                " title like '${request.title}' and " +
                " description like '${request.description}' " +
                " limit 1"
        val insertAdmin = "insert into members(" +
                " group_id, user_id)" +
                " values ( ($selectIdNewGroup), '${request.userId}');"
        jdbcTemplate.execute(insertAdmin)
    } catch (e: Exception) {
        null
    }

    fun deleteFromGroup(id: Long) = try {
        val sql = "delete from groups " +
                " where group_id = $id"
        jdbcTemplate.execute(sql)
    } catch (e: Exception) {
        null
    }

    fun selectFromGroup(id: Long) = try {
        val sql = "select * from groups " +
                " where group_id = $id"
        jdbcTemplate.queryForList(sql)
    } catch (e: Exception) {
        null
    }

    fun selectGroupsByMember(id: Long) = try {
        val sql = "select group_id, user_id, title, description" +
                " from pgroups " +
                " where group_id in (select group_id from members where user_id = $id)"
        jdbcTemplate.queryForList(sql)
    } catch (e: Exception) {
        null
    }
}