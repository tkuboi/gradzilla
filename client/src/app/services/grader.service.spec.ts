import { TestBed } from '@angular/core/testing';

import { GraderService } from './grader.service';

describe('GraderService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: GraderService = TestBed.get(GraderService);
    expect(service).toBeTruthy();
  });
});
