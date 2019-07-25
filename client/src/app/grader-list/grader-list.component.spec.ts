import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GraderListComponent } from './grader-list.component';

describe('GraderListComponent', () => {
  let component: GraderListComponent;
  let fixture: ComponentFixture<GraderListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GraderListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GraderListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
