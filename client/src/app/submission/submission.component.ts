import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';
import { AuthenticationService, AssignmentsService, UploadService } from  '@/services';

@Component({
  selector: 'app-submission',
  templateUrl: './submission.component.html',
  styleUrls: ['./submission.component.css']
})
export class SubmissionComponent implements OnInit {
  assignment;
  form: FormGroup;
  error: string;
  username: string;
  uploadResponse = { status: '', message: '', filePath: '' };

  constructor(private formBuilder: FormBuilder,
              private uploadService: UploadService,
              private assignmentsService: AssignmentsService,
              private authenticationService: AuthenticationService,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.paramMap.subscribe(params => {
     this.assignment = this.assignmentsService.assignments[+params.get('assignmentId')];
    });
    this.username = this.authenticationService.currentUserValue.username;
    this.form = this.formBuilder.group({
      submission: ['']
    });
  }

  onFileChange(event) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.form.get('submission').setValue(file);
    }
  }

  onSubmit() {
    const formData = new FormData();
    formData.append('file', this.form.get('submission').value);
    formData.append('filename', this.form.get('submission').value.name);
    formData.append('course', this.assignment.course);
    formData.append('assignmentName', this.assignment.name);
    formData.append('assignmentId', this.assignment.id);

    this.uploadService.upload(formData, this.username).subscribe(
      (res) => this.uploadResponse = res,
      (err) => this.error = err
    );
  }
}
