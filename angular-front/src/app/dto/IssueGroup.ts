export class IssueGroup {
  issue_id: number = 0
  member_id: number | null = 0
  title: string = ""
  description: string = ""
  status: string = ""
  priority: number = 0
  date_start: string = ""
  date_finish: string | null = ""
  full_name: string = ""
  email: string = ""

  constructor(issue_id: number,
              member_id: number,
              title: string,
              description: string,
              status: string,
              priority: number,
              date_start: string,
              date_finish: string,
              full_name: string,
              email: string) {
    this.issue_id = issue_id
    this.member_id = member_id
    this.title = title
    this.description = description
    this.status = status
    this.priority = priority
    this.date_start = date_start
    this.date_finish = date_finish
    this.full_name = full_name
    this.email = email
  }
}
