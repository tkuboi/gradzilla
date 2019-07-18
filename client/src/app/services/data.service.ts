import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  private assignmentIdx = new BehaviorSubject<any>(0);
  currentAssignmentIdx = this.assignmentIdx.asObservable();
  assignments: Array<any>;

  constructor() { }

  changeAssignmentIdx(idx) {
    this.assignmentIdx.next(idx);
  }
}
