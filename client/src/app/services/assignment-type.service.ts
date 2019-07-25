import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { config } from '@/config';

@Injectable({
  providedIn: 'root'
})
export class AssignmentTypeService {

constructor(private httpClient: HttpClient) { }

  getAll(): Observable<any> {
    return this.httpClient.get(`${config.apiUrl}/assignment-types`);
  }
}
