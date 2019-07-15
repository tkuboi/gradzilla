import { TestBed } from '@angular/core/testing';

import { SubmissionResultService } from './submission-result.service';

describe('SubmissionResultService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: SubmissionResultService = TestBed.get(SubmissionResultService);
    expect(service).toBeTruthy();
  });
});
