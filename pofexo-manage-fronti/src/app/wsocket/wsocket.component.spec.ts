import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WsocketComponent } from './wsocket.component';

describe('WsocketComponent', () => {
  let component: WsocketComponent;
  let fixture: ComponentFixture<WsocketComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WsocketComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WsocketComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
