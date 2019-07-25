import { TestBed } from '@angular/core/testing';

import { AssignmentTypeService } from './assignment-type.service';

describe('AssignmentTypeService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: AssignmentTypeService = TestBed.get(AssignmentTypeService);
    expect(service).toBeTruthy();
  });
});
