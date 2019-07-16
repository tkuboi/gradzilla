import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { MatButtonModule, MatCardModule, MatInputModule, MatListModule, MatToolbarModule } from '@angular/material';

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

@NgModule({
  declarations: [
    AppComponent,
    SubmissionComponent,
    AssignmentListComponent,
    CourseListComponent,
    SubmissionListComponent,
    RegisterComponent,
    LoginComponent,
    AlertComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,
    MatButtonModule,
    MatCardModule,
    MatInputModule,
    MatListModule,
    MatToolbarModule
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },

    // provider used to create fake backend
    //fakeBackendProvider
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
