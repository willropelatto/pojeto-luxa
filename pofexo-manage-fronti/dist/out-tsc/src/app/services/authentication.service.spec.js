import { TestBed, inject } from '@angular/core/testing';
import { AuthenticationService } from './authentication.service';
describe('AuthenticationService', function () {
    beforeEach(function () {
        TestBed.configureTestingModule({
            providers: [AuthenticationService]
        });
    });
    it('should be created', inject([AuthenticationService], function (service) {
        expect(service).toBeTruthy();
    }));
});
//# sourceMappingURL=authentication.service.spec.js.map