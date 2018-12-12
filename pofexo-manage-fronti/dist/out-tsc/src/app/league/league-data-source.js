import { BehaviorSubject, of } from "rxjs";
import { catchError, finalize } from "rxjs/operators";
var LeagueDataSource = /** @class */ (function () {
    function LeagueDataSource(leagueService) {
        var _this = this;
        this.leagueService = leagueService;
        this.leagueSubject = new BehaviorSubject([]);
        this.loadingSubject = new BehaviorSubject(false);
        this.cont = 0;
        this.loading$ = this.loadingSubject.asObservable();
        this.data = this.leagueSubject.asObservable().subscribe(function (t) { return _this.data = t; });
    }
    LeagueDataSource.prototype.connect = function (collectionViewer) {
        return this.leagueSubject.asObservable();
    };
    LeagueDataSource.prototype.disconnect = function (collectionViewer) {
        this.leagueSubject.complete();
        this.loadingSubject.complete();
    };
    LeagueDataSource.prototype.leaguesCount = function () {
        return this.cont;
    };
    LeagueDataSource.prototype.loadLeagues = function (sortDirection, pageIndex, pageSize) {
        var _this = this;
        this.loadingSubject.next(true);
        this.leagueService.getLeagues(pageIndex, pageSize)
            .pipe(catchError(function () { return of([]); }), finalize(function () { return _this.loadingSubject.next(false); }))
            .subscribe(function (leagues) {
            _this.leagueSubject.next(leagues['content']);
            _this.cont = leagues['totalElements'];
        });
    };
    return LeagueDataSource;
}());
export { LeagueDataSource };
//# sourceMappingURL=league-data-source.js.map