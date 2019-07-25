import {
Component,
ComponentRef,
OnInit,
ComponentFactoryResolver,
Type,
ViewChild,
ViewContainerRef
  } from '@angular/core';
import { Router } from '@angular/router';

import { CourseService, DataService } from '@/services';
import { EditCourseComponent } from '@/edit-course';
import { AssignmentAdminComponent } from '@/assignment-admin';

@Component({
  selector: 'app-course-admin',
  templateUrl: './course-admin.component.html',
  styleUrls: ['./course-admin.component.css']
})
export class CourseAdminComponent implements OnInit {
  @ViewChild('container', {read: ViewContainerRef, static: false}) container: ViewContainerRef;

  // Keep track of list of generated components for removal purposes
  components = [];
  editCourseComponent = EditCourseComponent;
  assignmentAdminComponent = AssignmentAdminComponent;

  courses;
  course;

  constructor(private courseService: CourseService,
              private componentFactoryResolver: ComponentFactoryResolver,
              private dataService: DataService) { }

  ngOnInit() {
    this.courseService.getAll().subscribe(data => this.courses = data);
  }

  ngOnDestory() {
    this.removeComponent();
  }

  onEditCourse(course) {
    if (this.components.length > 0) {
      this.removeComponent();
    }
    let componentFactory = this.componentFactoryResolver.resolveComponentFactory(EditCourseComponent);
    let componentRef: ComponentRef<EditCourseComponent> = this.container.createComponent(componentFactory);
    let currentComponent = componentRef.instance;

    currentComponent.selfRef = currentComponent;

    // prividing parent Component reference to get access to parent class methods
    currentComponent.compInteraction = this;

    // add reference for newly created component
    this.components.push(componentRef);

    if (course) {
      currentComponent.setCourse(course);
    }
  }

  onSelectCourse(course) {
    console.log("onSelectCourse");
    this.dataService.changeCourse(course);
    if (this.components.length > 0) {
      this.removeComponent();
    }
    let componentFactory = this.componentFactoryResolver.resolveComponentFactory(AssignmentAdminComponent);
    let componentRef: ComponentRef<AssignmentAdminComponent> = this.container.createComponent(componentFactory);
    let currentComponent = componentRef.instance;

    currentComponent.selfRef = currentComponent;

    // prividing parent Component reference to get access to parent class methods
    currentComponent.compInteraction = this;

    // add reference for newly created component
    this.components.push(componentRef);

    if (course) {
      currentComponent.setCourse(course);
    }
  }

  removeComponent() {
    // Find the component
    let idx = this.components.length - 1;
    let component = this.components[idx];

    if (idx >=0 ) {
      // Remove component from both view and array
      this.container.remove(this.container.indexOf(component));
      this.components.splice(idx, 1);
    }
  }

}
