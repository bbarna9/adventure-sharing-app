import { Component } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { AuthService } from '../../services/Auth/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [MatToolbarModule, MatButtonModule, MatIconModule],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss',
})
export class NavbarComponent {
  user: any = null;

  constructor(public authService: AuthService, private router: Router) {}

  handleLogout() {
    this.authService.logout();
    //this.router.navigate("/");
  }

  ngOnInit() {
    this.authService.authSubject.subscribe((data) => {
      this.user = data.user;
    });
  }
}
