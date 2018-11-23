import { DataSource } from "@angular/cdk/table";
import { League } from "../beans/league";
import { BehaviorSubject, Observable, of } from "rxjs";
import { LeagueService } from "../services/league.service";
import { CollectionViewer } from "@angular/cdk/collections";
import { catchError, finalize } from "rxjs/operators";

export class LeagueDataSource implements DataSource<League>{

    private leagueSubject = new BehaviorSubject<League[]>([]);
    private loadingSubject = new BehaviorSubject<boolean>(false);
    private cont = 0;

    public loading$ = this.loadingSubject.asObservable();
    public data = this.leagueSubject.asObservable().subscribe(t => this.data = t);

    constructor(private leagueService: LeagueService) { }

    connect(collectionViewer: CollectionViewer): Observable<League[]> {
        return this.leagueSubject.asObservable();
    }

    disconnect(collectionViewer: CollectionViewer): void {
        this.leagueSubject.complete();
        this.loadingSubject.complete();
    }

    leaguesCount() {
        return this.cont;
    }

    loadLeagues(sortDirection: string, pageIndex: number, pageSize: number) {
        this.loadingSubject.next(true);

        this.leagueService.getLeagues(pageIndex, pageSize)
            .pipe(
                catchError(() => of([])),
                finalize(() => this.loadingSubject.next(false))
            )
            .subscribe(leagues => {
                this.leagueSubject.next(leagues['content'])
                this.cont = leagues['totalElements']
            }
            );
    }

}
