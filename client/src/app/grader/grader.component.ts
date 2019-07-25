import { Component, OnInit, Input } from '@angular/core';

import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, FormControl } from '@angular/forms';

import { DataService, UploadService } from '@/services';
import { Assignment } from '@/models';
import { Removable } from '@/interfaces';

@Component({
  selector: 'app-grader',
  templateUrl: './grader.component.html',
  styleUrls: ['./grader.component.css']
})
export class GraderComponent implements OnInit {
  assignment;
  @Input() grader;
  form: FormGroup;
  error: string;
  uploadResponse = { status: '', message: '', filePath: '' };

  public index: number;
  public selfRef: GraderComponent;

  //interface for Parent-Child interaction
  public compInteraction: Removable;

  constructor(private formBuilder: FormBuilder,
              private uploadService: UploadService,
              private dataService: DataService,
              private route: ActivatedRoute) { }

  ngOnInit() {
    this.dataService.currentAssignmentIdx.subscribe(idx => {
      if (this.dataService.assignments) {
        this.assignment = this.dataService.assignments[+idx];
        } else {
        this.assignment = null;
        }
    });

    //this.assignment = new Assignment();
    //this.assignment.id = 2
    //this.assignment.course = 'CPE202';

    this.form = this.formBuilder.group({
      seq: [''],
      script: [''],
      scriptName: [''],
      program: [''],
      args: [''],
      copy: [''],
      pylint: ['']
    });  
  }

  onFileChange(event) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.form.get('script').setValue(file);
    }
  }

  onSubmit() {
    console.log(this.form.get('seq').value);
    console.log(this.form.get('copy').value);
    const formData = new FormData();
    formData.append('file', this.form.get('script').value);
    formData.append('filename', this.form.get('script').value.name);
    formData.append('course', this.assignment.course);
    formData.append('seq', this.form.get('seq').value);
    formData.append('program', this.form.get('program').value);
    formData.append('args', this.form.get('args').value);
    formData.append('copy', this.form.get('copy').value === true ? '1' : '0');
    formData.append('pylint', this.form.get('pylint').value === true ? '1' : '0');

    this.uploadService.uploadGrader(formData, this.assignment.id).subscribe(
      (res) => this.uploadResponse = res,
      (err) => this.error = err
      );
  }

  removeMe() {
    this.compInteraction.removeComponent();
  }
}
