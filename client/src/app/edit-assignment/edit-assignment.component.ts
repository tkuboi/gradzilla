import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, FormControl } from '@angular/forms';

import { DataService, AssignmentService, AssignmentTypeService } from '@/services';
import { Assignment } from '@/models';
import { Removable } from '@/interfaces';

@Component({
  selector: 'app-edit-assignment',
  templateUrl: './edit-assignment.component.html',
  styleUrls: ['./edit-assignment.component.css']
})
export class EditAssignmentComponent implements OnInit {
  course;
  assignment: Assignment = new Assignment();
  assignmentTypes: Array<any>;
  form: FormGroup;
  openDate = new FormControl(new Date());
  dueDate = new FormControl(new Date());
  minDate = new Date();
  maxDate = new Date();
  submitResponse;
  error;

  public index: number;
  public selfRef: EditAssignmentComponent;

  //interface for Parent-Child interaction
  public compInteraction: Removable;

  constructor(private formBuilder: FormBuilder,
              private dataService: DataService,
              private assignmentTypeService: AssignmentTypeService,
              private assignmentService: AssignmentService) { }

  ngOnInit() {
    this.course = this.dataService.currentCourseName;
    this.minDate.setHours(0);
    this.maxDate.setDate( this.maxDate.getDate() + 100 );
    this.assignmentTypeService.getAll().subscribe(data => this.assignmentTypes = data);
    this.form = this.formBuilder.group({
      course: [this.course.name],
      name: [this.assignment.name],
      type: [this.assignment.type],
      open: [],
      due: []
    });
  }

  // convenience getter for easy access to form fields
  get f() { return this.form.controls; }

  toTimestamp(val) {
    return Date.parse(val);
  }

  setAssignment(assignment) {
    this.assignment = assignment;
    this.course = this.assignment.course;
    if (this.assignment.open) {
      this.openDate = new FormControl(new Date(this.assignment.open));
    }
    if (this.assignment.due) {
      this.dueDate = new FormControl(new Date(this.assignment.due));
    }
  }

  onSubmit() {
    console.log(this.openDate.value);
    console.log(Date.parse(this.openDate.value).toString());
    this.assignment = new Assignment();
    this.assignment.course = this.f.course.value;
    this.assignment.name = this.f.name.value;
    this.assignment.type = this.f.type.value;
    this.assignment.open = this.toTimestamp(this.openDate.value);
    this.assignment.due = this.toTimestamp(this.dueDate.value);

    this.assignmentService.save(this.assignment).subscribe(
      (res) => {this.submitResponse = res;},
      (err) => this.error = err
    );
  }

  onDelete() {
    this.assignmentService.delete(this.assignment).subscribe(
      (res) => {this.submitResponse = res;},
      (err) => this.error = err
    );
  }

  removeMe() {
    this.compInteraction.removeComponent();
  }
}
