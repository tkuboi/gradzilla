import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { config } from '@/config';

@Injectable({
  providedIn: 'root'
})
export class SubmissionResultService {

  constructor(private http: HttpClient) { }

  getAll(user, assignment): Observable<any> {
    return this.http.get(`${config.apiUrl}/submissions/${user}/${assignment}`);
  }

  getOne(id): Observable<any> {
    return this.http.get(`${config.apiUrl}/submission/${id}`)
  }
}
