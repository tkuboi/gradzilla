import { Component, OnInit, Input } from '@angular/core';
import { SubmissionResultService } from '../submission-result.service'

@Component({
  selector: 'app-submission-list',
  templateUrl: './submission-list.component.html',
  styleUrls: ['./submission-list.component.css']
})
export class SubmissionListComponent implements OnInit {
  submissions;
  @Input() assignmentId: number;
  @Input() user: string;

  constructor(private submissionResultService: SubmissionResultService) { }

  ngOnInit() {
    this.submissionResultService.getAll(this.user, this.assignmentId).subscribe(data => {
     this.submissions = data;
    });
  }

}
