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
export class GraderService {

  constructor(private http: HttpClient) { }

  getAll(assignmentId): Observable<any> {
    return this.http.get(`${config.apiUrl}/graders/${assignmentId}`);
  }

  delete(grader): Observable<any> {
    return this.http.post(`${config.apiUrl}/graders/delete/${grader.id}`, grader, httpOptions);
  }
}
