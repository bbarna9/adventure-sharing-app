import { Component, Inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormField, MatInput, MatLabel } from '@angular/material/input';
import { MatRadioModule } from '@angular/material/radio';
import { MatSelectModule } from '@angular/material/select';
import { AdventureService } from '../../services/Adventure/adventure.service';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';

@Component({
  selector: 'app-update-adventure-form',
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
  templateUrl: './update-adventure-form.component.html',
  styleUrl: './update-adventure-form.component.scss',
})
export class UpdateAdventureFormComponent {
  adventureItem: any = {
    title: '',
    location: '',
    description: '',
    imageUrl: '',
    cost: '',
    monthOfTravel: '',
    lengthOfTravel: '',
  };

  constructor(
    @Inject(MAT_DIALOG_DATA) public adventure: any,
    public adventureService: AdventureService
  ) {}

  onSubmit() {
    this.adventureService.updateAdventure(this.adventureItem).subscribe();
  }

  ngOnInit() {
    this.adventureItem = this.adventure;
  }
}
