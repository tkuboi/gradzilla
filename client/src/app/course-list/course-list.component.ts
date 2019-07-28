import { Component, OnInit, OnDestroy } from '@angular/core';
import { CourseService, AuthenticationService, DataService } from '@/services';

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit, OnDestroy {
  courses;
  user;
  selected;
  subscriptions: Array<any> = new Array<any>();

  constructor(
    private dataService: DataService,
    private courseService: CourseService,
    private authenticationService: AuthenticationService
  ) { }

  ngOnInit() {
    this.user = this.authenticationService.currentUserValue;
    if (this.user.role === 'ADMIN') {
      this.subscriptions.push(
        this.courseService.getAll().subscribe(data => {
          this.courses = data;
          this.selected = data[0].name;
          this.dataService.changeCourse(this.selected);
        }));
    }
    else {
      this.subscriptions.push(
        this.courseService.getAllByUser(this.user.username).subscribe(data => {
          this.courses = data;
          this.selected = data[0].name;
          this.dataService.changeCourse(this.selected);
        }));
    }
  }

  onCourseChange(event) {
    console.log(event);
    if (event.value) {
     this.dataService.changeCourse(event.value);
    }
  }

  ngOnDestroy() {
    for (let subscription of this.subscriptions) {
      subscription.unsubscribe();
    }
  }  
}
