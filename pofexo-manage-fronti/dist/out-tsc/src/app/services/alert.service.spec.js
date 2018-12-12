import { TestBed, inject } from '@angular/core/testing';
import { AlertService } from './alert.service';
describe('AlertService', function () {
    beforeEach(function () {
        TestBed.configureTestingModule({
            providers: [AlertService]
        });
    });
    it('should be created', inject([AlertService], function (service) {
        expect(service).toBeTruthy();
    }));
});
//# sourceMappingURL=alert.service.spec.js.map