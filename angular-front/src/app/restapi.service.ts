import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RestapiService {

  constructor(private http: HttpClient) {
  }

  login(username: string, password: string) {
    localStorage.setItem('username', username);
    localStorage.setItem('password', password);
    const headers = new HttpHeaders({Authorization: 'Basic ' + btoa(username + ':' + password)});
    return this.http.get("http://localhost:8080/user/auth", {headers, responseType: 'text' as 'json'})
  }

  getTitle() {
    let username = 'admin'
    let password = 'admin'
    const headers = new HttpHeaders({Authorization: 'Basic ' + btoa(username + ':' + password)});
    return this.http.get("http://localhost:8080/auth", {headers, responseType: 'text' as 'json'})
  }

  get(endpoint: string) {
    const headers = new HttpHeaders({
        Authorization: 'Basic ' + btoa(
          localStorage.getItem('username') + ':' + localStorage.getItem('password'))
      }
    );
    return this.http.get("http://localhost:8080" + endpoint, {headers, responseType: 'text' as 'json'})
  }

  post(endpoint: string, request: any) {
    const headers = new HttpHeaders({
        Authorization: 'Basic ' + btoa(
          localStorage.getItem('username') + ':' + localStorage.getItem('password'))
      }
    );
    return this.http.post("http://localhost:8080" + endpoint, request,{headers, responseType: 'text' as 'json'})
  }
}
