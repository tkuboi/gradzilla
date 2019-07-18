import { Component, OnInit, Input } from '@angular/core';
import { AssignmentService, DataService } from '@/services';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-assignment-list',
  templateUrl: './assignment-list.component.html',
  styleUrls: ['./assignment-list.component.css']
})
export class AssignmentListComponent implements OnInit {
  @Input() course: string;
  assignments: Array<any>;
  selected: string;

  constructor(private assignmentService: AssignmentService,
                  private dataService: DataService,
                  private route: ActivatedRoute) { }

  ngOnInit() {
    this.assignmentService.getAll(this.course).subscribe(data => {
     this.assignments = data;
     this.dataService.assignments = data;
    });
  }

  onClick(idx) {
    let assignment = this.dataService.assignments[idx];
    this.dataService.changeAssignmentIdx(idx);
    this.selected = assignment;
  }
}
