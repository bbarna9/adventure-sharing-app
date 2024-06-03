import { Component } from '@angular/core';
import { AdventureCardComponent } from '../../components/adventure-card/adventure-card.component';
import { MatDialog } from '@angular/material/dialog';
import { CreateAdventureFormComponent } from '../../components/create-adventure-form/create-adventure-form.component';
import { MatIcon } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { AuthService } from '../../services/Auth/auth.service';
import { AdventureService } from '../../services/Adventure/adventure.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [AdventureCardComponent, MatIcon, MatButtonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent {
  adventures = [1, 1, 1, 1, 1, 1, 1, 1];

  constructor(
    public dialog: MatDialog,
    public authService: AuthService,
    public adventureService: AdventureService
  ) {}

  handleOpenCreateAdventureDialog() {
    this.dialog.open(CreateAdventureFormComponent);
  }

  ngOnInit() {
    this.authService.getUserProfile().subscribe();
    this.adventureService.getAdventures().subscribe();
    this.adventureService.adventureSubject.subscribe((state) => {
      this.adventures = state.adventures;
    });
  }
}
