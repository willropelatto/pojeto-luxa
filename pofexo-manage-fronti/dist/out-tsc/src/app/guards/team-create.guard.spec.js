import { TestBed, inject } from '@angular/core/testing';
import { TeamCreateGuard } from './team-create.guard';
describe('TeamCreateGuard', function () {
    beforeEach(function () {
        TestBed.configureTestingModule({
            providers: [TeamCreateGuard]
        });
    });
    it('should ...', inject([TeamCreateGuard], function (guard) {
        expect(guard).toBeTruthy();
    }));
});
//# sourceMappingURL=team-create.guard.spec.js.map