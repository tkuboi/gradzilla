import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { config } from '@/config';

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
}
