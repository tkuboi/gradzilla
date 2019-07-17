import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { config } from '@/config';

@Injectable({
  providedIn: 'root'
})
export class AssignmentService {

  constructor(private http: HttpClient) { }

  getAll(course): Observable<any> {
      return this.http.get(`${config.apiUrl}/assignments/${course}`);
  }
}
