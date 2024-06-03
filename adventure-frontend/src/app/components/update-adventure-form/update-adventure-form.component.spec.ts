import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateAdventureFormComponent } from './update-adventure-form.component';

describe('UpdateAdventureFormComponent', () => {
  let component: UpdateAdventureFormComponent;
  let fixture: ComponentFixture<UpdateAdventureFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdateAdventureFormComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(UpdateAdventureFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
