package ru.issuemanager.service

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import ru.issuemanager.dao.UserDao
import ru.issuemanager.dto.RegistrationUserRequest

@Service
class UserService(
    private val userDao: UserDao
) {
    fun registration(request: RegistrationUserRequest) = try {
        userDao.insertUser(request)
    } catch (e: Exception) {
        null
    }

    fun getInfo(id: Long) = try {
        userDao.selectUserById(id)
    } catch (e: Exception) {
        null
    }

    fun getIdByName(name: String?) = try {
        if(name != null) {
            userDao.selectIdByName(name)
        } else {
            null
        }
    } catch (e: Exception) {
        null
    }
}