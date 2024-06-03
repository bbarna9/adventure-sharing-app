import { Component, Input } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatDialog } from '@angular/material/dialog';
import { MatIcon } from '@angular/material/icon';
import { UpdateAdventureFormComponent } from '../update-adventure-form/update-adventure-form.component';
import { AdventureService } from '../../services/Adventure/adventure.service';

@Component({
  selector: 'app-adventure-card',
  standalone: true,
  imports: [MatCardModule, MatButtonModule, MatIcon],
  templateUrl: './adventure-card.component.html',
  styleUrl: './adventure-card.component.scss',
})
export class AdventureCardComponent {
  @Input() adventure: any;

  constructor(
    public dialog: MatDialog,
    public adventureService: AdventureService
  ) {}

  handleOpenEditAdventureForm() {
    this.dialog.open(UpdateAdventureFormComponent, { data: this.adventure });
  }

  handleDeleteAdventure() {
    this.adventureService.deleteAdventure(this.adventure.id).subscribe();
  }
}
