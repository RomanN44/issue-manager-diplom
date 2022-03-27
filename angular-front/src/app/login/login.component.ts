import { Component, OnInit } from '@angular/core';
import { RestapiService } from '../restapi.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  id: Object = 0
  username: string = "";
  password: string = "";
  message: any

  constructor(private service: RestapiService,private router:Router) { }

  ngOnInit() {
  }

  doLogin() {
    let resp = this.service.login(this.username, this.password);
    resp.subscribe(res => {
      localStorage.setItem('id', String(res));
      this.router.navigate(["/home"])
    });
  }
}
