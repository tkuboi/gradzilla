import { Component, OnInit } from '@angular/core';
import { CourseService } from '@/services';

@Component({
  selector: 'app-course-list',
  templateUrl: './course-list.component.html',
  styleUrls: ['./course-list.component.css']
})
export class CourseListComponent implements OnInit {
  courses;

  constructor(private courseService: CourseService) { }

  ngOnInit() {
    this.courses = this.courseService.getAll().subscribe(data => {
     this.courses = data;
    });;
  }

}
