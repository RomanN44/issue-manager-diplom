package ru.issuemanager.service

import mu.KotlinLogging
import org.springframework.stereotype.Service
import ru.issuemanager.dao.GroupDao
import ru.issuemanager.dao.MemberDao
import ru.issuemanager.dao.UserDao
import ru.issuemanager.dto.AddMemberRequest
import ru.issuemanager.dto.CreateGroupRequest

private val logger = KotlinLogging.logger {}

@Service
class GroupService(
    private val groupDao: GroupDao,
    private val memberDao: MemberDao,
    private val userDao: UserDao
) {
    fun createGroup(request: CreateGroupRequest) = try {
        groupDao.insertGroup(request)
    } catch (e: Exception) {
        logger.error { e.message }
        null
    }

    fun deleteGroup(id: Long) = try {
        groupDao.deleteFromGroup(id)
    } catch (e: Exception) {
        logger.error { e.message }
        null
    }

    fun getGroup(id: Long) = try {
        groupDao.selectFromGroup(id)
    } catch (e: Exception) {
        logger.error { e.message }
        null
    }

    fun addMember(request: AddMemberRequest) = try {
        memberDao.insertMember(request)
    } catch (e: Exception) {
        logger.error { e.message }
        null
    }

    fun deleteMember(groupId: Long, userId: Long) = try {
        memberDao.deleteMember(groupId, userId)
    } catch (e: Exception) {
        logger.error { e.message }
        null
    }

    fun getMembersGroup(id: Long) = try {
        userDao.selectUserByGroup(id)
    } catch (e: Exception) {
        logger.error { e.message }
        null
    }

    fun getGroupsByMember(id: Long) = try {
        groupDao.selectGroupsByMember(id)
    } catch (e: Exception) {
        logger.error { e.message }
        null
    }

}