import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CardpojetoComponent } from './cardpojeto.component';

describe('CardpojetoComponent', () => {
  let component: CardpojetoComponent;
  let fixture: ComponentFixture<CardpojetoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CardpojetoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CardpojetoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
