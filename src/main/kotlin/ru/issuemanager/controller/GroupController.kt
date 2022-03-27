package ru.issuemanager.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import ru.issuemanager.dto.AddMemberRequest
import ru.issuemanager.dto.CreateGroupRequest
import ru.issuemanager.service.GroupService

@RestController
@CrossOrigin(origins = ["*"])
class GroupController(
    private val groupService: GroupService
) {
    @PostMapping("/group/createGroup")
    fun createGroup(@RequestBody request: CreateGroupRequest) = try {
        groupService.createGroup(request)
        ResponseEntity<String>(HttpStatus.OK)
    } catch (e: Exception) {
        ResponseEntity<String>(HttpStatus.BAD_REQUEST)
    }

    @GetMapping("/group/deleteGroup/{id}")
    fun deleteGroup(@PathVariable id: Long) = try {
        groupService.deleteGroup(id)
        ResponseEntity<String>(HttpStatus.OK)
    } catch (e: Exception) {
        ResponseEntity<String>(HttpStatus.BAD_REQUEST)
    }

    @GetMapping("/group/getGroup/{id}")
    fun getGroup(@PathVariable id: Long) = try {
        groupService.getGroup(id)
        ResponseEntity<String>(HttpStatus.OK)
    } catch (e: Exception) {
        ResponseEntity<String>(HttpStatus.BAD_REQUEST)
    }

    @PostMapping("/group/addMember")
    fun addMember(@RequestBody request: AddMemberRequest) = try {
        groupService.addMember(request)
        ResponseEntity<String>(HttpStatus.OK)
    } catch (e: Exception) {
        ResponseEntity<String>(HttpStatus.BAD_REQUEST)
    }

    @GetMapping("/group/deleteMember/{groupId}/{userId}")
    fun deleteMember(@PathVariable groupId: Long, @PathVariable userId: Long) = try {
        groupService.deleteMember(groupId, userId)
        ResponseEntity<String>(HttpStatus.OK)
    } catch (e: Exception) {
        ResponseEntity<String>(HttpStatus.BAD_REQUEST)
    }

    @GetMapping("/group/getMembersGroup/{id}")
    fun getMembersGroup(@PathVariable id: Long) = try {
        groupService.getMembersGroup(id)
        ResponseEntity<String>(HttpStatus.OK)
    } catch (e: Exception) {
        ResponseEntity<String>(HttpStatus.BAD_REQUEST)
    }

    @GetMapping("/group/getGroupsByMember/{id}")
    fun getGroupsByMember(@PathVariable id: Long) = try {
        groupService.getGroupsByMember(id)
        ResponseEntity<String>(HttpStatus.OK)
    } catch (e: Exception) {
        ResponseEntity<String>(HttpStatus.BAD_REQUEST)
    }
}