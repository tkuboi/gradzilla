import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { AssignmentUserService, AuthenticationService, DataService } from '@/services';
import { ActivatedRoute, Router } from '@angular/router';

import { User } from '@/models';
import {Removable } from '@/interfaces';
import { formatDate } from '@/helpers';

@Component({
  selector: 'app-assignment-list',
  templateUrl: './assignment-list.component.html',
  styleUrls: ['./assignment-list.component.css']
})
export class AssignmentListComponent implements OnInit, OnDestroy {
  course: string;
  assignments: Array<any>;
  selected: string;
  user: User;
  subscriptions: Array<any> = new Array<any>();

  constructor(private assignmentUserService: AssignmentUserService,
              private dataService: DataService,
              private authenticationService: AuthenticationService,
              private router: Router,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.user = this.authenticationService.currentUserValue;
    this.subscriptions.push(
      this.dataService.currentCourse.subscribe(acourse => {
        this.course = acourse;
        this.assignmentUserService.getAll(this.user.username, this.course).subscribe(data => {
          this.assignments = data;
          this.dataService.assignments = data;
          this.dataService.changeAssignmentIdx(data.length - 1);
        });
      }));
  }

  onClick(idx) {
    let assignment = this.dataService.assignments[idx];
    this.dataService.changeAssignmentIdx(idx);
    this.selected = assignment;
  }

  formatDate(dt) {
    return formatDate(dt);
  }

  ngOnDestroy() {
    for (let subscription of this.subscriptions) {
      subscription.unsubscribe();
    }
  }
}
