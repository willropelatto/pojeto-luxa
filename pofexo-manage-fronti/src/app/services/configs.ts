import { HttpHeaders, HttpParams } from "@angular/common/http";
import { Observable, of } from "rxjs";


const baseUrl = 'http://localhost:9595';

const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

export class Configs {

    static loginUrl = baseUrl + '/login';
    static managerUrl = baseUrl + '/user';
    static teamUrl = baseUrl + '/team';
    static playerUrl = baseUrl + '/player';
    static leagueUrl = baseUrl + '/league';
    static mktUrl = baseUrl + '/market';
    static notificationUrl = baseUrl + '/notification';    

    static getHttpOptions() {
        return httpOptions;
    }

    static getHttpHeaders(): HttpHeaders {
        return new HttpHeaders()
            .set('Accept', 'application/json')
            .set('Content-Type', 'application/json');
        //bearer in future
    }

    static getHttpParams(currentPage, pageSize): HttpParams {
        return new HttpParams()
            .set('page', currentPage)
            .set('size', pageSize);
        // .set('sort', sortBy);
    }

    /**
     * Handle Http operation that failed.
     * Let the app continue.
     * @param operation - name of the operation that failed
     * @param result - optional value to return as the observable result
     */
    static handleError<T>(operation = 'operation', result?: T) {
        return (error: any): Observable<T> => {
            // TODO: send the error to remote logging infrastructure
            console.error(error); // log to console instead
            // TODO: better job of transforming error for user consumption
            //this.log(`${operation} failed: ${error.message}`);
            // Let the app keep running by returning an empty result.
            return of(result as T);
        };
    }

    static log(msg) {
        //TODO: criar vergonha na cara.
        console.log(msg);        
    }

}



