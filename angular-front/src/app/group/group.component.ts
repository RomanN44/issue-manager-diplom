import {Component, OnInit} from '@angular/core';
import {Group} from "../dto/Group";
import {RestapiService} from "../restapi.service";

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css']
})
export class GroupComponent implements OnInit {

  allGroups: Group[] = []
  createGroupRequest = {
    title: null,
    description: null,
    userId: null
  }

  constructor(private restapi: RestapiService) {
    this.getGroups()
    // @ts-ignore
    this.createGroupRequest.userId = localStorage.getItem('id')
  }

  ngOnInit(): void {
  }

  getGroups() {
    this.restapi.get('/group/getGroupsByMember/' + localStorage.getItem('id'))
      .subscribe((data: any) => {
          this.allGroups = []
          let res = JSON.parse(data)
          res.forEach((group: Group) => this.allGroups.push(group))
        }
      )
  }

  isAdmin(group: Group) {
    if (group.user_id.toString() == localStorage.getItem('id')) {
      return "Вы администратор"
    } else {
      return ""
    }
  }

  createGroup() {
    if (this.createGroupRequest.title !== null) {
      this.restapi.post('/group/createGroup', this.createGroupRequest)
        .subscribe((data: any) => {
          this.getGroups()
        })
    }
  }
}
