export class Issue {
  issue_id: number = 0
  member_id: number | null = 0
  title: string = ""
  description: string = ""
  status: string = ""
  date_start: string = ""
  date_finish: string | null = ""
  group_id: number | null = 0

  constructor(issue_id: number,
              member_id: number,
              title: string,
              description: string,
              status: string,
              date_start: string,
              date_finish: string,
              group_id: number) {

      this.issue_id = issue_id
      this.member_id = member_id
      this.title = title
      this.description = description
      this.status = status
      this.date_start = date_start
      this.date_finish = date_finish
      this.group_id = group_id
  }
}
