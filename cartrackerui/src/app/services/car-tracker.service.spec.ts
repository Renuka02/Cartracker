import { TestBed, inject } from '@angular/core/testing';

import { CarTrackerService } from './car-tracker.service';

describe('CarTrackerService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [CarTrackerService]
    });
  });

  it('should be created', inject([CarTrackerService], (service: CarTrackerService) => {
    expect(service).toBeTruthy();
  }));
});
