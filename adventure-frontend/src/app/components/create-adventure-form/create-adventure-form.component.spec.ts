import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateAdventureFormComponent } from './create-adventure-form.component';

describe('CreateAdventureFormComponent', () => {
  let component: CreateAdventureFormComponent;
  let fixture: ComponentFixture<CreateAdventureFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CreateAdventureFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(CreateAdventureFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
