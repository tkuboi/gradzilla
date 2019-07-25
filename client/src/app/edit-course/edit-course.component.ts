import { Component, OnInit, Input, Type } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, FormControl } from '@angular/forms';

import { DataService, CourseService } from '@/services';
import { Course } from '@/models';
import { Removable } from '@/interfaces';

@Component({
  selector: 'app-edit-course',
  templateUrl: './edit-course.component.html',
  styleUrls: ['./edit-course.component.css']
})
export class EditCourseComponent implements OnInit {
  courseId: string;
  course: Course = new Course();
  form: FormGroup;
  submitResponse;
  error;

  public index: number;
  public selfRef: EditCourseComponent;

  //interface for Parent-Child interaction
  public compInteraction: Removable;

  constructor(private formBuilder: FormBuilder,
              private dataService: DataService,
              private courseService: CourseService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.form = this.formBuilder.group({
      name: [''],
      short: [''],
      description: ['']
    });
  }

  setCourse(course) {
    this.course = course;
  }

  // convenience getter for easy access to form fields
  get f() { return this.form.controls; }

  onSubmit() {
    //this.course = new Course();
    this.course.name = this.f.name.value;
    this.course.shortDescription = this.f.short.value;
    this.course.description = this.f.description.value;
    this.courseService.save(this.course).subscribe(
    (res) => {this.submitResponse = res;},
      (err) => this.error = err
    );
  }

  onDelete() {
    this.courseService.delete(this.course).subscribe(
    (res) => {this.submitResponse = res;},
      (err) => this.error = err
    );
  }

  removeMe() {
    this.compInteraction.removeComponent();
  }
}

