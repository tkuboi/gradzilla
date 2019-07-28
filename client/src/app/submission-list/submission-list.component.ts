import { Component, OnInit, Input, OnDestroy } from '@angular/core';
import { AuthenticationService, DataService, SubmissionResultService } from '@/services'

@Component({
  selector: 'app-submission-list',
  templateUrl: './submission-list.component.html',
  styleUrls: ['./submission-list.component.css']
})
export class SubmissionListComponent implements OnInit, OnDestroy {
  submissions;
  assignment;
  user: string;
  subscriptions: Array<any> = new Array<any>();

  constructor(
    private authenticationService: AuthenticationService,
    private submissionResultService: SubmissionResultService,
    private dataService: DataService
  ) { }

  ngOnInit() {
    this.user = this.authenticationService.currentUserValue.username;
    this.subscriptions.push(
      this.dataService.currentAssignmentIdx.subscribe(idx => {
        if (this.dataService.assignments) {
          this.assignment = this.dataService.assignments[+idx];
            if (this.assignment) {
              this.submissionResultService
                .getAll(this.user, this.assignment.id).subscribe(data => {
                  this.submissions = data;
              });
            }
        }
        else {
          this.assignment = null;
          this.submissions = null;
        }
      console.log(this.assignment);
    }));
  }

  onFocus() {
    alert('Focused!');
  }

  ngOnDestroy() {
    for (let subscription of this.subscriptions) {
      subscription.unsubscribe();
    }
  }
}
