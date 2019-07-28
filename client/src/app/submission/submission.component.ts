import { Component, OnInit, Input, OnDestroy, ViewChild, ElementRef, Renderer2 } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgForm, FormBuilder, FormGroup } from '@angular/forms';

import { AuthenticationService, DataService, UploadService } from  '@/services';
import { MatDialog } from '@angular/material/dialog';

import {ResultDialogComponent} from '@/result-dialog';

export interface ResponseData {
  status: string; message: string; filePath: string, score: number; 
}

@Component({
  selector: 'app-submission',
  templateUrl: './submission.component.html',
  styleUrls: ['./submission.component.css']
})
export class SubmissionComponent implements OnInit, OnDestroy {
  @ViewChild('subform', {static: false}) subform: NgForm;
  @ViewChild('fileInput', {static: false}) fileElemRef: ElementRef;
  @ViewChild('errorMessage', {static: false}) emessageElemRef: ElementRef;
  @ViewChild('uploadStatus', {static: false}) ustatusElemRef: ElementRef;
 
  assignment;
  assIdx;
  form: FormGroup;
  error: string;
  username: string;
  uploadResponse: ResponseData = { status: '', message: '', filePath: '', score: null};
  subscriptions: Array<any> = new Array<any>();

  constructor(private formBuilder: FormBuilder,
              private renderer: Renderer2,
              private dialog: MatDialog,
              private uploadService: UploadService,
              private dataService: DataService,
              private authenticationService: AuthenticationService,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.subscriptions.push(this.dataService.currentAssignmentIdx.subscribe(idx => {
      this.assIdx = idx;
      if (this.dataService.assignments) {
        this.assignment = this.dataService.assignments[+idx];
      } else {
        this.assignment = null;
      }
    }));
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

    this.subscriptions.push(this.uploadService.uploadSubmission(formData, this.username)
      .subscribe(
      (res) => {
        this.uploadResponse = res;
        this.dataService.changeAssignmentIdx(this.assIdx);
        if (this.uploadResponse.status === 'success'
            || this.uploadResponse.status === 'error') {
          this.openDialog();
        }
      },
      (err) => this.error = err
    ));
  }

  reset() {
    this.subform.resetForm();
    this.fileElemRef.nativeElement.value = "";
    if (this.emessageElemRef) {
      this.renderer.removeChild(this.emessageElemRef.nativeElement,
                                this.emessageElemRef.nativeElement);
    }
    if (this.ustatusElemRef) {
      this.renderer.removeChild(this.ustatusElemRef.nativeElement,
                                this.ustatusElemRef.nativeElement);
    }
  }

  openDialog() {
    this.dialog.open(ResultDialogComponent, {
      data: this.uploadResponse
    });
  }

  ngOnDestroy() {
    for (let subscription of this.subscriptions) {
      subscription.unsubscribe();
    }
  }
}
