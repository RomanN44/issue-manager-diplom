package ru.issuemanager.dao

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import ru.issuemanager.domain.User
import ru.issuemanager.dto.RegistrationUserRequest
import java.sql.ResultSet

@Repository
class UserDao(
    private val jdbcTemplate: JdbcTemplate
) {
    fun selectUserByGroup(id: Long) = try {
        val sql = "select user_id, username, email, full_name " +
                " from users " +
                " where user_id in " +
                " (select user_id from members where group_id = $id) "
        jdbcTemplate.queryForList(sql)
    } catch (e: Exception) {
        null
    }

    fun selectUserById(id: Long) = try {
        val sql = "select user_id, username, email, full_name " +
                " from users " +
                " where user_id = $id "
        jdbcTemplate.queryForObject(sql) { rs: ResultSet, _: Int ->
            User(rs.getLong("user_id"),
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("full_name"))
        }
    } catch (e: Exception) {
        null
    }

    fun insertUser(request: RegistrationUserRequest) = try {
        val sql = "insert into users(" +
                " username, password, email, full_name)" +
                " VALUES ('${request.username}', '${request.password}'," +
                " '${request.email}', '${request.fullName}');"
        jdbcTemplate.execute(sql)
    } catch (e: Exception) {
        null
    }

    fun selectIdByName(name: String) = try {
        val sql = "select user_id " +
                " from users " +
                " where username like '$name' "
        jdbcTemplate.queryForObject(sql, Long::class.java)
    } catch (e: Exception) {
        null
    }
}