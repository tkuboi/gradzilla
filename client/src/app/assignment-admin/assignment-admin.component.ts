import { Component, OnInit, OnDestroy, Input, ComponentRef, ComponentFactoryResolver, ViewContainerRef, ViewChild } from '@angular/core';
import { AssignmentService, DataService } from '@/services';

import { Removable } from '@/interfaces';
import { EditAssignmentComponent } from '@/edit-assignment';
import { GraderListComponent } from '@/grader-list';
import { formatDate } from '@/helpers';

@Component({
  selector: 'app-assignment-admin',
  templateUrl: './assignment-admin.component.html',
  styleUrls: ['./assignment-admin.component.css']
})
export class AssignmentAdminComponent implements OnInit, OnDestroy {
  @ViewChild('container', {read: ViewContainerRef, static: false}) container: ViewContainerRef;

  // Keep track of list of generated components for removal purposes
  components = [];

  course: string;
  assignments: Array<any>;
  selected: string;

  public index: number;
  public selfRef: AssignmentAdminComponent;

  //interface for Parent-Child interaction
  public compInteraction: Removable;

  subscriptions: Array<any> = new Array<any>();

  constructor(private assignmentService: AssignmentService,
              private dataService: DataService,
              private componentFactoryResolver: ComponentFactoryResolver) { }

  ngOnInit() {
    this.subscriptions.push(
      this.dataService.currentCourse.subscribe(acourse => {
        this.course = acourse;
        this.assignmentService.getAll(this.course).subscribe(data => {
          this.assignments = data;
          this.dataService.assignments = data;
          this.dataService.changeAssignmentIdx(0);
        });
      }));
  }

  ngOnDestroy() {
    this.removeComponent();
    for (let subscription of this.subscriptions) {
      subscription.unsubscribe();
    }
  }

  setCourse(course) {
    this.course = course;
    this.assignmentService.getAll(this.course).subscribe(data => {
      this.assignments = data;
      this.dataService.assignments = data;
      this.dataService.changeAssignmentIdx(0);
    });
  }

  onClick(assignment) {
    if (this.components.length > 0) {
      this.removeComponent();
    }
    let componentFactory = this.componentFactoryResolver.resolveComponentFactory(GraderListComponent);
    let componentRef: ComponentRef<GraderListComponent> = this.container.createComponent(componentFactory);
    let currentComponent = componentRef.instance;

    currentComponent.selfRef = currentComponent;

    // prividing parent Component reference to get access to parent class methods
    currentComponent.compInteraction = this;

    // add reference for newly created component
    this.components.push(componentRef);

    if (assignment) {
      currentComponent.setAssignment(assignment);
    }

  }

  onEdit(assignment) {
    if (this.components.length > 0) {
      this.removeComponent();
    }
    let componentFactory = this.componentFactoryResolver.resolveComponentFactory(EditAssignmentComponent);
    let componentRef: ComponentRef<EditAssignmentComponent> = this.container.createComponent(componentFactory);
    let currentComponent = componentRef.instance;

    currentComponent.selfRef = currentComponent;

    // prividing parent Component reference to get access to parent class methods
    currentComponent.compInteraction = this;

    // add reference for newly created component
    this.components.push(componentRef);

    if (assignment) {
      currentComponent.setAssignment(assignment);
    }

  }


  removeMe() {
    this.compInteraction.removeComponent()
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

  formatDate(dt) {
    return formatDate(dt);
  }
}
