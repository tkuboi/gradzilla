import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SubmissionComponent } from './submission/submission.component';
import { AssignmentListComponent } from './assignment-list/assignment-list.component';
import { CourseListComponent } from './course-list/course-list.component';
import { LoginComponent } from './login';
import { RegisterComponent } from './register';
import { AuthGuard } from './helpers';

const routes: Routes = [
  {
    path: '',
    component: CourseListComponent,
    canActivate: [AuthGuard]
  },
  { path: 'assignments/:courseName', component: AssignmentListComponent },
  { path: 'submission/:assignmentId', component: SubmissionComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
