import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

import { config } from '@/config';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json'
    })};

@Injectable({
  providedIn: 'root'
})
export class CourseService {

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(`${config.apiUrl}/courses`);
  }
    
  getAllByUser(username): Observable<any> {
    return this.http.get(`${config.apiUrl}/${username}/courses`);
  }

  save(course): Observable<any> {
    return this.http.post(`${config.apiUrl}/courses/put`, course, httpOptions);
  }

  delete(course): Observable<any> {
    return this.http.post(`${config.apiUrl}/courses/delete/${course.name}`, course, httpOptions);
  }
}
