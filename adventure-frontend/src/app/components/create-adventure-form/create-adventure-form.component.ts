import { Component, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatInput, MatFormField, MatLabel } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatRadioModule } from '@angular/material/radio';
import { AdventureService } from '../../services/Adventure/adventure.service';

@Component({
  selector: 'app-create-adventure-form',
  standalone: true,
  imports: [
    MatInput,
    MatFormField,
    MatLabel,
    FormsModule,
    MatButtonModule,
    MatSelectModule,
    MatRadioModule,
  ],
  templateUrl: './create-adventure-form.component.html',
  styleUrl: './create-adventure-form.component.scss',
})
export class CreateAdventureFormComponent {
  adventureItem: any = {
    title: '',
    location: '',
    description: '',
    imageUrl: '',
    cost: '',
    monthOfTravel: '',
    lengthOfTravel: '',
  };

  constructor(private adventureService: AdventureService) {}

  onSubmit() {
    this.adventureService.createAdventure(this.adventureItem).subscribe();
  }
}
