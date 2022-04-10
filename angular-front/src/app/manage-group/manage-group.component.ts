import {Component, OnInit} from '@angular/core';
import {RestapiService} from "../restapi.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Group} from "../dto/Group";
import {User} from "../dto/User";

@Component({
  selector: 'app-manage-group',
  templateUrl: './manage-group.component.html',
  styleUrls: ['./manage-group.component.css']
})
export class ManageGroupComponent implements OnInit{

  id: number = 0
  group: Group | null = null
  allMembers: User[] = []
  newMemberRequest = {
    userId: null,
    groupId: null
  }

  constructor(private restapi: RestapiService,
              private router: Router,
              private route: ActivatedRoute) {
    this.route.queryParams.subscribe(params => {
      this.id = params['group'];
    })
    this.restapi.get('/group/getGroup/' + this.id).subscribe(
      (data: any) => this.group = JSON.parse(data)
    )
    this.getMembers()
    // @ts-ignore
    this.newMemberRequest.groupId = this.id
  }

  ngOnInit(): void {
  }

  getMembers() {
    this.restapi.get('/group/getMembersGroup/' + this.id)
      .subscribe((data: any) => {
          this.allMembers = []
          let res = JSON.parse(data)
          res.forEach((user: User) => this.allMembers.push(user))
        }
      )
  }

  deleteMember(user_id: number) {
    if (user_id == this.group?.user_id) {
      alert('Нельзя удалить себя из группы!')
    } else {
      this.restapi.get('/group/deleteMember/' + this.id + '/' + user_id)
        .subscribe((data: any) => {
        this.getMembers()
      })
    }
  }

  deleteGroup() {
    this.restapi.get('/group/deleteGroup/' + this.id)
  }

  addMember() {
    if (this.newMemberRequest.userId !== null && this.newMemberRequest.userId != localStorage.getItem('id')) {
      this.restapi.post('/group/addMember', this.newMemberRequest)
        .subscribe((data: any) => {
          this.getMembers()
        })
    }
  }
}
