import { Injectable } from '@angular/core';
import { Subject, BehaviorSubject, Observable } from 'rxjs';

import { Course } from '@/models';

@Injectable({
  providedIn: 'root'
})
export class DataService {
  private assignmentIdx = new BehaviorSubject<any>(0);
  currentAssignmentIdx = this.assignmentIdx.asObservable();
  assignments: Array<any>;
  private course = new Subject<any>();
  currentCourse = this.course.asObservable();
  currentCourseName;
  private courseObj: BehaviorSubject<Course>;
  currentCourseObj: Observable<Course>;

  constructor() { }

  changeAssignmentIdx(idx) {
    this.assignmentIdx.next(idx);
  }

  changeCourse(name) {
    this.course.next(name);
    this.currentCourseName = name;
  }

  changeCourseObj(course) {
    if (!this.courseObj) {
      this.courseObj = new BehaviorSubject<Course>(course);
      this.currentCourseObj = this.courseObj.asObservable()
    } else {
      this.courseObj.next(course);
    }
  }
}
