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
export class AssignmentService {

  constructor(private http: HttpClient) { }

  getAll(course): Observable<any> {
    return this.http.get(`${config.apiUrl}/assignments/${course}`);
  }

  save(assignment): Observable<any> {
    console.log(assignment);
    return this.http.post(`${config.apiUrl}/assignments/put`, assignment, httpOptions);
  }

  delete(assignment): Observable<any> {
    return this.http.post(`${config.apiUrl}/assignments/delete/${assignment.id}`, assignment, httpOptions);
  }
}
