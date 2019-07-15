import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SubmissionResultService {
  url = "//localhost:8080/"

  constructor(private http: HttpClient) { }

  getAll(user, assignment): Observable<any> {
    return this.http.get(this.url + 'submissions/' + user + '/' + assignment);
  }

  getOne(id): Observable<any> {
    return this.http.get(this.url + 'submission/' + id)
  }
}
