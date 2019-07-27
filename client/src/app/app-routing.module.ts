import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SubmissionComponent } from './submission/submission.component';
import { AssignmentListComponent } from './assignment-list/assignment-list.component';
import { CourseListComponent } from './course-list/course-list.component';
import { LoginComponent } from './login';
import { RegisterComponent } from './register';
import { AuthGuard } from './helpers';
import { EditAssignmentComponent } from './edit-assignment/edit-assignment.component';
import { EditCourseComponent } from './edit-course/edit-course.component';
import { GraderComponent } from './grader/grader.component';
import { BaseComponent } from './base/base.component';
import { AdminComponent } from './admin/admin.component';

const routes: Routes = [
  {
    path: '',
    component: BaseComponent,
    canActivate: [AuthGuard]
  },
  { path: 'login', component: LoginComponent },
  { path: 'admin', component: AdminComponent, canActivate: [AuthGuard] }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
