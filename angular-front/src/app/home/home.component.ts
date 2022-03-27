import {Component, OnInit} from '@angular/core';
import {RestapiService} from '../restapi.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  info: { userId: number, username: string, email: string | null, fullName: string | null } =
    {
      userId: 0,
      username: '',
      email: null,
      fullName: null
    }

  constructor(private service: RestapiService) {
    this.getInfo()
  }

  ngOnInit() {
  }

  getInfo() {
    let resp = this.service.get("/user/getInfo/" + localStorage.getItem('id'))
    resp.subscribe((data: any) => {
      let res = JSON.parse(data)
      this.info.userId = res.userId
      this.info.username = res.username
      this.info.email = res.email
      this.info.fullName = res.fullName
    })
  }

  showIfNull(field: string | null) {
    if(field === null) {
      return "Неизвестно"
    } else {
      return field
    }
  }



}
