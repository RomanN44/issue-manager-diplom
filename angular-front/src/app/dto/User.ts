export class User {
  user_id: number = 0
  username: string = ""
  email: string = ""
  full_name: string = ""

  constructor(user_id: number,
              username: string,
              email: string,
              full_name: string) {

    this.user_id = user_id
    this.username = username
    this.email = email
    this.full_name = full_name
  }
}
