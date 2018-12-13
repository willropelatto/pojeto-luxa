import { TestBed, inject } from '@angular/core/testing';
import { LeagueService } from './league.service';
describe('LeagueService', function () {
    beforeEach(function () {
        TestBed.configureTestingModule({
            providers: [LeagueService]
        });
    });
    it('should be created', inject([LeagueService], function (service) {
        expect(service).toBeTruthy();
    }));
});
//# sourceMappingURL=league.service.spec.js.map