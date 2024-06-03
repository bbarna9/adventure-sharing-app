import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './components/navbar/navbar.component';
import { FooterComponent } from './components/footer/footer.component';
import { HomeComponent } from './pages/home/home.component';
import { MatIcon } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { AuthComponent } from './components/auth/auth.component';
import { AuthService } from './services/Auth/auth.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    RouterOutlet,
    NavbarComponent,
    FooterComponent,
    AuthComponent,
    HomeComponent,
    MatIcon,
    MatButtonModule,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
})
export class AppComponent {
  title = 'adventure-frontend';

  user: any = null;

  constructor(public authService: AuthService) {}

  ngOnInit() {
    this.authService.getUserProfile().subscribe();
    this.authService.authSubject.subscribe((auth) => {
      this.user = auth.user;
    });
  }
}
