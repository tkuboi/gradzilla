import { Component, OnInit, ComponentRef, ComponentFactoryResolver, ViewContainerRef, ViewChild } from '@angular/core';

import { DataService, GraderService } from '@/services';
import { Removable } from '@/interfaces';
import { GraderComponent } from '@/grader';

@Component({
  selector: 'app-grader-list',
  templateUrl: './grader-list.component.html',
  styleUrls: ['./grader-list.component.css']
})
export class GraderListComponent implements OnInit {
@ViewChild('container', {read: ViewContainerRef, static: false}) container: ViewContainerRef;

  // Keep track of list of generated components for removal purposes
  components = [];

  graders;
  assignment;

  public index: number;
  public selfRef: GraderListComponent;

  //interface for Parent-Child interaction
  public compInteraction: Removable;

  constructor(private dataService: DataService,
              private graderService: GraderService,
              private componentFactoryResolver: ComponentFactoryResolver) { }

  ngOnInit() {
  }

  ngOnDestroy() {
    this.removeComponent();
  }

  setAssignment(assignment) {
    console.log(assignment);
    this.assignment = assignment;
    if (this.assignment) {
      this.graderService.getAll(this.assignment.id).subscribe( data =>
      this.graders = data);
    }
  }

  onDelete(grader) {
    console.log(grader);
    this.graderService.delete(grader).subscribe(
    data => {this.setAssignment(this.assignment);},
    err => {alert(err);});
  }

  onAdd() {
    let componentFactory = this.componentFactoryResolver.resolveComponentFactory(GraderComponent);
    let componentRef: ComponentRef<GraderComponent> = this.container.createComponent(componentFactory);
    let currentComponent = componentRef.instance;
  }

  removeMe() {
    this.compInteraction.removeComponent();
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
