import { Component, OnInit } from '@angular/core';
import {Group} from "../dto/Group";
import {RestapiService} from "../restapi.service";

@Component({
  selector: 'app-group',
  templateUrl: './group.component.html',
  styleUrls: ['./group.component.css']
})
export class GroupComponent implements OnInit {

  allGroups: Group[] = []

  constructor(private restapi: RestapiService) {
    this.getGroups()
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

}
