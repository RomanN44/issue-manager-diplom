export class Group {
  group_id: number = 0
  user_id: number = 0
  title: string = ""
  description: string = ""

  constructor(group_id: number,
              user_id: number,
              title: string,
              description: string) {

    this.group_id = group_id
    this.user_id = user_id
    this.title = title
    this.description = description
  }
}
