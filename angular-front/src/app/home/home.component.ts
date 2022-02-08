import {Component, OnInit} from '@angular/core';
import {RestapiService} from '../restapi.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  title: any;

  constructor(private service: RestapiService) {
  }

  ngOnInit() {
  }

  getTitle() {
    let resp = this.service.getTitle()
    resp.subscribe(data => this.title=data)
  }

}
