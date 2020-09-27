import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AlertService, AuthenticationService } from '@/services';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'Gradzilla';
  isAuthenticated: boolean;

  constructor(public authenticationService: AuthenticationService,
              public router: Router,
              protected alertService: AlertService){}

  ngOnInit() {
    this.isAuthenticated = (this.authenticationService.currentUserValue != null);
    this.authenticationService.currentUser.subscribe( user => {
      if (user) {
        this.isAuthenticated = true;
      } else {
        this.isAuthenticated = false;
      }
    });
  }

  onLogout() {
    this.authenticationService.logout();
    this.router.navigate(['/login']);
  }

}
