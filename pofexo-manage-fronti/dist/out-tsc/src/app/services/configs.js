import { HttpHeaders, HttpParams } from "@angular/common/http";
import { of } from "rxjs";
var baseUrl = 'http://localhost:9595';
var httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
var Configs = /** @class */ (function () {
    function Configs() {
    }
    Configs.getHttpOptions = function () {
        return httpOptions;
    };
    Configs.getHttpHeaders = function () {
        return new HttpHeaders()
            .set('Accept', 'application/json')
            .set('Content-Type', 'application/json');
        //bearer in future
    };
    Configs.getHttpParams = function (currentPage, pageSize) {
        return new HttpParams()
            .set('page', currentPage)
            .set('size', pageSize);
        // .set('sort', sortBy);
    };
    /**
     * Handle Http operation that failed.
     * Let the app continue.
     * @param operation - name of the operation that failed
     * @param result - optional value to return as the observable result
     */
    Configs.handleError = function (operation, result) {
        if (operation === void 0) { operation = 'operation'; }
        return function (error) {
            // TODO: send the error to remote logging infrastructure
            console.error(error); // log to console instead
            // TODO: better job of transforming error for user consumption
            //this.log(`${operation} failed: ${error.message}`);
            // Let the app keep running by returning an empty result.
            return of(result);
        };
    };
    Configs.log = function (msg) {
        //TODO: criar vergonha na cara.
        console.log(msg);
    };
    Configs.loginUrl = baseUrl + '/login';
    Configs.managerUrl = baseUrl + '/user';
    Configs.teamUrl = baseUrl + '/team';
    Configs.playerUrl = baseUrl + '/player';
    Configs.leagueUrl = baseUrl + '/league';
    Configs.mktUrl = baseUrl + '/market';
    Configs.notificationUrl = baseUrl + '/notification';
    return Configs;
}());
export { Configs };
//# sourceMappingURL=configs.js.map