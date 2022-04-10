import { Component, OnInit } from '@angular/core';
import {Group} from "../dto/Group";
import {RestapiService} from "../restapi.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  allGroups: Group[] = []

  constructor(private restapi: RestapiService,
              private router: Router) {
    this.getGroups()
  }

  ngOnInit(): void {
  }

  getGroups() {
    this.restapi.get('/group/getGroupsByMember/' + localStorage.getItem('id'))
      .subscribe((data: any) => {
          this.allGroups = []
          let res = JSON.parse(data)
          res.forEach((group: Group) => {
            if(group.user_id.toString() == localStorage.getItem('id')) {
              this.allGroups.push(group)
            }
          })
        }
      )
  }

  getManage(group: Group) {
    this.router.navigate(["manage"], {queryParams: {group: group.group_id}});
  }
}
