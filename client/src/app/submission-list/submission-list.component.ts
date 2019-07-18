import { Component, OnInit, Input } from '@angular/core';
import { DataService, SubmissionResultService } from '@/services'

@Component({
  selector: 'app-submission-list',
  templateUrl: './submission-list.component.html',
  styleUrls: ['./submission-list.component.css']
})
export class SubmissionListComponent implements OnInit {
  submissions;
  assignment;
  @Input() user: string;

  constructor(
    private submissionResultService: SubmissionResultService,
    private dataService: DataService
  ) { }

  ngOnInit() {
    this.dataService.currentAssignmentIdx.subscribe(idx => {
       this.assignment = this.dataService.assignments[+idx]; 
       this.submissionResultService
         .getAll(this.user, this.assignment.id).subscribe(data => {
           this.submissions = data;
         });
       console.log(this.assignment);
    });
  }

}
