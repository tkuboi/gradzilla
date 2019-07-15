import { Component, OnInit } from '@angular/core';
import { AssignmentService, AssignmentsService } from '@/services';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-assignment-list',
  templateUrl: './assignment-list.component.html',
  styleUrls: ['./assignment-list.component.css']
})
export class AssignmentListComponent implements OnInit {
  course: string;
  assignments: Array<any>;

  constructor(private assignmentService: AssignmentService,
                  private assignmentsService: AssignmentsService,
                  private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
     this.course = params.get('courseName');
    });
    this.assignmentService.getAll(this.course).subscribe(data => {
     this.assignments = data;
     this.assignmentsService.assignments = data;
    });
  }

}
