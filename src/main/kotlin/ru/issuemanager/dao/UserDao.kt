package ru.issuemanager.dao

import mu.KotlinLogging
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Repository
import ru.issuemanager.domain.User
import ru.issuemanager.dto.RegistrationUserRequest
import java.sql.ResultSet

private val logger = KotlinLogging.logger {}

@Repository
class UserDao(
    private val jdbcTemplate: JdbcTemplate,
    private val encoder: BCryptPasswordEncoder
) {
    fun selectUserByGroup(id: Long) = try {
        val sql = "select user_id, username, email, full_name " +
                " from users " +
                " where user_id in " +
                " (select user_id from members where group_id = $id) "
        jdbcTemplate.queryForList(sql)
    } catch (e: Exception) {
        logger.error { e.message }
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
        logger.error { e.message }
        null
    }

    fun insertUser(request: RegistrationUserRequest) = try {
        val sql = "insert into users(" +
                " username, password, email, full_name)" +
                " VALUES ('${request.username}', '${encoder.encode(request.password)}'," +
                " '${request.email}', '${request.fullName}');"
        jdbcTemplate.execute(sql)
    } catch (e: Exception) {
        logger.error { e.message }
        null
    }

    fun selectIdByName(name: String) = try {
        val sql = "select user_id " +
                " from users " +
                " where username like '$name' "
        jdbcTemplate.queryForObject(sql, Long::class.java)
    } catch (e: Exception) {
        logger.error { e.message }
        null
    }

    fun selectEmailByAdminGroup(id: Long) = try {
        val sql = "select email from users " +
                "where user_id = (select user_id from groups where group_id = $id)"
        jdbcTemplate.queryForObject(sql, String::class.java)
    } catch (e: Exception) {
        logger.error { e.message }
        null
    }
}