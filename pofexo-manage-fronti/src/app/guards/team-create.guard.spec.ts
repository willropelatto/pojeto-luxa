import { TestBed, async, inject } from '@angular/core/testing';

import { TeamCreateGuard } from './team-create.guard';

describe('TeamCreateGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [TeamCreateGuard]
    });
  });

  it('should ...', inject([TeamCreateGuard], (guard: TeamCreateGuard) => {
    expect(guard).toBeTruthy();
  }));
});
