import { Component, OnInit } from '@angular/core';
import { CourseService, AuthenticationService } from '@/services';

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit {
  courses;
  user;
  selected;

  constructor(
    private courseService: CourseService,
    private authenticationService: AuthenticationService
  ) { }

  ngOnInit() {
    this.user = this.authenticationService.currentUserValue;
    this.courseService.getAllByUser(this.user.username).subscribe(data => {
     this.courses = data;
     this.selected = data[0].name;
     });
  }

}
