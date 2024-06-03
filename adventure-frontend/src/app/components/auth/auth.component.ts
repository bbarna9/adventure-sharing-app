import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatInput, MatFormField, MatLabel } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatRadioModule } from '@angular/material/radio';
import { AuthService } from '../../services/Auth/auth.service';

@Component({
  selector: 'app-auth',
  standalone: true,
  imports: [
    MatInput,
    MatFormField,
    MatLabel,
    FormsModule,
    CommonModule,
    MatButtonModule,
    MatSelectModule,
    MatRadioModule,
    ReactiveFormsModule,
  ],
  templateUrl: './auth.component.html',
  styleUrl: './auth.component.scss',
})
export class AuthComponent {
  isRegister = true;

  constructor(public authService: AuthService) {}

  registerForm = new FormGroup({
    fullName: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [
      Validators.required,
      Validators.minLength(6),
    ]),
  });

  loginForm = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
  });

  handleRegister() {
    this.authService.register(this.registerForm.value).subscribe({
      next: (response) => {
        localStorage.setItem('jwt', response.jwt);
        this.authService.getUserProfile().subscribe();
      },
    });
  }

  handleLogin() {
    this.authService.login(this.loginForm.value).subscribe({
      next: (response) => {
        localStorage.setItem('jwt', response.jwt);
        this.authService.getUserProfile().subscribe();
        console.log('Login successful, response: ' + response);
      },
    });
  }

  togglePanel() {
    this.isRegister = !this.isRegister;
  }
}
