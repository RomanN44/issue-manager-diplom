import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RestapiService {

  constructor(private http: HttpClient) {
  }

  login(username: string, password: string) {
    const headers = new HttpHeaders({Authorization: 'Basic ' + btoa(username + ':' + password)});
    return this.http.get("http://localhost:8080/auth", {headers, responseType: 'text' as 'json'})
  }

  getTitle() {
    let username = 'admin'
    let password = 'admin'
    const headers = new HttpHeaders({Authorization: 'Basic ' + btoa(username + ':' + password)});
    return this.http.get("http://localhost:8080/auth", {headers, responseType: 'text' as 'json'})
  }
}
