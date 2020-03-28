import { TestBed } from '@angular/core/testing';

import { AssignmentUserService } from './assignment-user.service';

describe('AssignmentUserService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AssignmentUserService = TestBed.get(AssignmentUserService);
    expect(service).toBeTruthy();
  });
});
