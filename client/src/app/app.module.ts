import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { MatButtonModule, MatCardModule, MatIconModule, MatInputModule, MatListModule, MatToolbarModule, MatProgressSpinnerModule, MatNativeDateModule } from '@angular/material';
import {MatSelectModule} from '@angular/material/select';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatDividerModule} from '@angular/material/divider';
import {MatTabsModule} from '@angular/material/tabs';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import {MatDialogModule} from '@angular/material/dialog';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SubmissionComponent } from '@/submission';
import { AssignmentListComponent } from '@/assignment-list';
import { CourseListComponent } from '@/course-list';
import { SubmissionListComponent } from '@/submission-list';
import { RegisterComponent } from '@/register';
import { LoginComponent } from '@/login';
import { AlertComponent } from '@/alert';

// used to create fake backend
import { JwtInterceptor, ErrorInterceptor } from '@/helpers';
import { EditAssignmentComponent } from './edit-assignment/edit-assignment.component';
import { GraderComponent } from './grader/grader.component';
import { BaseComponent } from './base/base.component';
import { AdminComponent } from './admin/admin.component';
import { GraderListComponent } from './grader-list/grader-list.component';
import { EditCourseComponent } from './edit-course/edit-course.component';
import { CourseAdminComponent } from './course-admin/course-admin.component';
import { AssignmentAdminComponent } from './assignment-admin/assignment-admin.component';
import { ResultDialogComponent } from './result-dialog/result-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    SubmissionComponent,
    AssignmentListComponent,
    CourseListComponent,
    SubmissionListComponent,
    RegisterComponent,
    LoginComponent,
    AlertComponent,
    EditAssignmentComponent,
    GraderComponent,
    BaseComponent,
    AdminComponent,
    GraderListComponent,
    EditCourseComponent,
    CourseAdminComponent,
    AssignmentAdminComponent,
    ResultDialogComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    MatButtonModule,
    MatCardModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule,
    MatProgressSpinnerModule,
    MatSelectModule,
    MatExpansionModule,
    MatSidenavModule,
    MatGridListModule,
    MatDividerModule,
    MatTabsModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatSlideToggleModule,
    MatDialogModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },

    // provider used to create fake backend
    //fakeBackendProvider
  ],
  entryComponents: [
    EditCourseComponent,
    EditAssignmentComponent,
    AssignmentAdminComponent,
    GraderComponent,
    GraderListComponent,
    ResultDialogComponent
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
