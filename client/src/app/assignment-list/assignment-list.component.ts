import { Component, OnInit, Input } from '@angular/core';
import { AssignmentService, AuthenticationService, DataService } from '@/services';
import { ActivatedRoute, Router } from '@angular/router';

import { User } from '@/models';
import {Removable } from '@/interfaces';

@Component({
  selector: 'app-assignment-list',
  templateUrl: './assignment-list.component.html',
  styleUrls: ['./assignment-list.component.css']
})
export class AssignmentListComponent implements OnInit {
  course: string;
  assignments: Array<any>;
  selected: string;
  user: User;

  constructor(private assignmentService: AssignmentService,
              private dataService: DataService,
              private authenticationService: AuthenticationService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.user = this.authenticationService.currentUserValue;
    this.dataService.currentCourse.subscribe(acourse => {
      this.course = acourse;
      this.assignmentService.getAll(this.course).subscribe(data => {
        this.assignments = data;
        this.dataService.assignments = data;
        this.dataService.changeAssignmentIdx(0);
      });
    });
  }

  onClick(idx) {
    let assignment = this.dataService.assignments[idx];
    this.dataService.changeAssignmentIdx(idx);
    this.selected = assignment;
  }

}
