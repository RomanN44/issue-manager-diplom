import {Component, OnInit} from '@angular/core';
import {RestapiService} from "../restapi.service";
import {Issue} from "../dto/Isuue";

@Component({
  selector: 'app-issue',
  templateUrl: './issue.component.html',
  styleUrls: ['./issue.component.css']
})
export class IssueComponent implements OnInit {

  allIssues: Issue[] = []

  constructor(private restapi: RestapiService) {
    this.getIssues()
  }

  ngOnInit(): void {
  }

  getIssues() {
    this.restapi.get('/issue/get/byUser/' + localStorage.getItem('id'))
      .subscribe((data: any) => {
          this.allIssues = []
          let res = JSON.parse(data)
          res.forEach((issue: Issue) => this.allIssues.push(issue))
        }
      )
  }

  changeStatus(issue: Issue) {
    let newStatus: string = issue.status
    switch (issue.status) {
      case 'ToDo': newStatus = 'InProgress'; break
      case 'InProgress': newStatus = 'InTest'; break
      case 'InTest': newStatus = 'Done'; break
    }
    console.log(newStatus)
    console.log(issue.status)
    this.restapi.post('/issue/changeStatus', {issueId: issue.issue_id, status: newStatus})
      .subscribe((data: any) => {
          this.getIssues()
        })
  }
}
