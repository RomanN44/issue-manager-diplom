import {Component, OnInit} from '@angular/core';
import {RestapiService} from "../restapi.service";
import {ActivatedRoute, Router} from "@angular/router";
import {Group} from "../dto/Group";
import {User} from "../dto/User";
import {IssueGroup} from "../dto/IssueGroup";
import {Report} from "../dto/Report";
import {ExcelService} from "../excel.service";

@Component({
  selector: 'app-manage-group',
  templateUrl: './manage-group.component.html',
  styleUrls: ['./manage-group.component.css']
})
export class ManageGroupComponent implements OnInit {

  id: number = 0
  group: Group | null = null
  allMembers: User[] = []
  newMemberRequest = {
    userId: null,
    groupId: null
  }
  createIssueRequest = {
    groupId: null,
    title: null,
    description: null,
    priority: null
  }
  assignIssueRequest = {
    userId: null,
    issueId: null,
    groupId: null
  }
  filterIssue = {
    issue_id: "",
    title: "",
    description: "",
    status: "",
    full_name: ""
  }
  optionsIssues: { title: string, id: number }[] = []
  optionsMembers: { full_name: string, id: number }[] = []
  options = ['', 'ToDo', 'InProgress', 'InTest', 'Done']
  priorities = [
    {title: 'Первый', value: 1},
    {title: 'Второй', value: 2},
    {title: 'Третий', value: 3},
    {title: 'Четвертый', value: 4},
    {title: 'Пятый', value: 5},
  ]
  allIssues: IssueGroup[] = []
  visibleIssues: IssueGroup[] = []
  report: Report[] = []

  constructor(private restapi: RestapiService,
              private excel: ExcelService,
              private router: Router,
              private route: ActivatedRoute) {
    this.route.queryParams.subscribe(params => {
      this.id = params['group'];
    })
    this.restapi.get('/group/getGroup/' + this.id).subscribe(
      (data: any) => this.group = JSON.parse(data)
    )
    this.getMembers()
    this.getIssues()
    // @ts-ignore
    this.newMemberRequest.groupId = this.id
    // @ts-ignore
    this.createIssueRequest.groupId = this.id
    // @ts-ignore
    this.assignIssueRequest.groupId = this.id
  }

  ngOnInit(): void {
  }

  getMembers() {
    this.restapi.get('/group/getMembersGroup/' + this.id)
      .subscribe((data: any) => {
          this.allMembers = []
          this.optionsMembers = []
          let res = JSON.parse(data)
          res.forEach((user: User) => {
            this.allMembers.push(user)
            this.optionsMembers.push({full_name: user.full_name, id: user.user_id})
          })
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
          this.getIssues()
        })
    }
  }

  deleteGroup() {
    this.restapi.get('/group/deleteGroup/' + this.id)
      .subscribe((data: any) => {
        this.router.navigate(["admin"]);
      })
  }

  addMember() {
    if (this.newMemberRequest.userId !== null && this.newMemberRequest.userId != localStorage.getItem('id')) {
      this.restapi.post('/group/addMember', this.newMemberRequest)
        .subscribe((data: any) => {
          this.getMembers()
          this.newMemberRequest.userId = null
        })
    }
  }

  getIssues() {
    this.restapi.get('/issue/get/byGroup/' + this.id)
      .subscribe((data: any) => {
          this.allIssues = []
          this.optionsIssues = []
          let res = JSON.parse(data)
          res.forEach((issue: IssueGroup) => {
            this.allIssues.push(issue)
            if (issue.member_id === null) {
              this.optionsIssues.push({title: issue.title, id: issue.issue_id})
            }
          })
          this.visibleIssues = this.allIssues
        }
      )
  }

  createIssue() {
    if (this.createIssueRequest.title !== null) {
      this.restapi.post('/issue/create', this.createIssueRequest)
        .subscribe((data: any) => {
          this.getIssues()
          this.createIssueRequest.title = null
          this.createIssueRequest.description = null
          this.createIssueRequest.priority = null
        })
    }
  }

  checkMember(name: string | null) {
    if (name === null) {
      return "Не назначено"
    } else {
      return name
    }
  }

  assignIssue() {
    if (this.assignIssueRequest.userId !== null && this.assignIssueRequest.issueId !== null) {
      this.restapi.post('/issue/assignIssue', this.assignIssueRequest)
        .subscribe((data: any) => {
          this.getIssues()
        })
    }
  }


  filter() {
    this.visibleIssues = []
    this.allIssues.forEach((issue: IssueGroup) => {
      if (this.findSubstr(issue.title, this.filterIssue.title) &&
        this.findSubstr(issue.description, this.filterIssue.description) &&
        this.findSubstr(issue.status, this.filterIssue.status) &&
        this.findSubstr(issue.full_name, this.filterIssue.full_name) &&
        this.findSubstr(String(issue.issue_id), String(this.filterIssue.issue_id))) {
        this.visibleIssues.push(issue)
      }
    })
  }

  hideDone(flag: boolean) {
    this.visibleIssues = []
    if (flag) {
      this.allIssues.forEach((issue: IssueGroup) => {
        if (issue.status !== 'Done') {
          this.visibleIssues.push(issue)
        }
      })
    } else {
      this.visibleIssues = this.allIssues
    }
  }

  getReport() {
    this.restapi.get('/group/getReport/' + this.id)
      .subscribe((data: any) => {
        this.report = []
        let res = JSON.parse(data)
        res.forEach((report: Report) => {
          this.report.push(report)
        })
        this.excel.exportAsExcelFile(this.report, 'report')
      })
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

  private findSubstr(str1: string, str2: string) {
    return !str1.toLowerCase().indexOf(str2.toLowerCase())
  }
}
