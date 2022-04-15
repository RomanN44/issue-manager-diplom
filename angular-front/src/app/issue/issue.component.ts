import {Component, OnInit} from '@angular/core';
import {RestapiService} from "../restapi.service";
import {Issue} from "../dto/Issue";

@Component({
  selector: 'app-issue',
  templateUrl: './issue.component.html',
  styleUrls: ['./issue.component.css']
})
export class IssueComponent implements OnInit {

  allIssues: Issue[] = []
  visibleIssues: Issue[] = []
  filterIssue = {
    issue_id: "",
    title: "",
    description: "",
    status: "",
    grouptitle: "",
  }
  options = ['', 'ToDo', 'InProgress', 'InTest', 'Done']

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
          this.visibleIssues = this.allIssues
        }
      )
  }

  changeStatus(issue: Issue) {
    let newStatus: string = issue.status
    switch (issue.status) {
      case 'ToDo':
        newStatus = 'InProgress';
        break
      case 'InProgress':
        newStatus = 'InTest';
        break
      case 'InTest':
        newStatus = 'Done';
        break
    }
    console.log(newStatus)
    console.log(issue.status)
    this.restapi.post('/issue/changeStatus', {issueId: issue.issue_id, status: newStatus})
      .subscribe((data: any) => {
        this.getIssues()
      })
  }

  filter() {
    this.visibleIssues = []
    this.allIssues.forEach((issue: Issue) => {
      if (this.findSubstr(issue.title, this.filterIssue.title) &&
        this.findSubstr(issue.description, this.filterIssue.description) &&
        this.findSubstr(issue.status, this.filterIssue.status) &&
        this.findSubstr(issue.grouptitle, this.filterIssue.grouptitle) &&
        this.findSubstr(String(issue.issue_id), String(this.filterIssue.issue_id))) {
        this.visibleIssues.push(issue)
      }
    })
  }

  private findSubstr(str1: string, str2: string) {
    return !str1.toLowerCase().indexOf(str2.toLowerCase())
  }

  hideDone(flag: boolean) {
    this.visibleIssues = []
    if(flag) {
      this.allIssues.forEach((issue: Issue) => {
        if(issue.status !== 'Done') {
          this.visibleIssues.push(issue)
        }
      })
    } else {
      this.visibleIssues = this.allIssues
    }
  }

  getStrByPriority(priority: number) {
    switch (priority) {
      case 1: return "Первый"
      case 2: return "Второй"
      case 3: return "Третий"
      case 4: return "Четвертый"
      default: return "Пятый"
    }
  }
}
