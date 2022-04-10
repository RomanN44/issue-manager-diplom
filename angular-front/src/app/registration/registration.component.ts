import {Component, OnInit} from '@angular/core';
import {RestapiService} from "../restapi.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  registrationRequest = {
    username: null,
    password: null,
    email: null,
    fullName: null
  }

  confirmPassword: string = ""

  constructor(private restapi: RestapiService,
              private router: Router) {
  }

  ngOnInit(): void {
  }

  goToLogin() {
    this.router.navigate(["/login"])
  }

  registration() {
    if(this.registrationRequest.password === this.confirmPassword) {
      this.restapi.post('/user/registration', this.registrationRequest)
        .subscribe((data: any) => {
          alert("Успешная регистрация!")
          this.router.navigate(["/login"])
        })
    }
  }
}
