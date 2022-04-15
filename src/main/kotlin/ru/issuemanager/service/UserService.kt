package ru.issuemanager.service

import mu.KotlinLogging
import org.springframework.stereotype.Service
import ru.issuemanager.dao.UserDao
import ru.issuemanager.dto.RegistrationUserRequest

private val logger = KotlinLogging.logger {}

@Service
class UserService(
    private val userDao: UserDao
) {
    fun registration(request: RegistrationUserRequest) = try {
        userDao.insertUser(request)
    } catch (e: Exception) {
        logger.error { e.message }
        null
    }

    fun getInfo(id: Long) = try {
        userDao.selectUserById(id)
    } catch (e: Exception) {
        logger.error { e.message }
        null
    }

    fun getIdByName(name: String?) = try {
        if(name != null) {
            userDao.selectIdByName(name)
        } else {
            null
        }
    } catch (e: Exception) {
        logger.error { e.message }
        null
    }
}