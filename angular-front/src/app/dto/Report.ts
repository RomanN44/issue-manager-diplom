export class Report{
  title: string = ""
  description: string = ""
  status: string = ""
  priority: number = 0
  date_start: string = ""
  date_finish: string | null = ""
  full_name: string = ""
  email: string = ""

  constructor(title: string,
              description: string,
              status: string,
              priority: number,
              date_start: string,
              date_finish: string,
              full_name: string,
              email: string) {
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
