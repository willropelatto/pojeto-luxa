import { Component, OnInit, ViewChild } from '@angular/core';
import { LeagueService } from '../services/league.service';
import { League } from '../beans/league';
import { MatPaginator, MatSnackBar } from '@angular/material';
import { LeagueDataSource } from './league-data-source';
import { tap } from 'rxjs/operators';
import { SelectionModel } from '@angular/cdk/collections';
import { MarketService } from '../services/market.service';

@Component({
  selector: 'app-league',
  templateUrl: './league.component.html',
  styleUrls: ['./league.component.css']
})
export class LeagueComponent implements OnInit {

  displayedColumns: string[] = ['select', 'name', 'abbrName'];
  dataSource: LeagueDataSource;

  @ViewChild(MatPaginator) paginator: MatPaginator;
  selection = new SelectionModel<League>(true, []);

  constructor(
    private leagueService: LeagueService,
    private mktService: MarketService,
    private snackBar: MatSnackBar
  ) { }

  /** Whether the number of selected elements matches the total number of rows. */
  isAllSelected() {
    const numSelected = this.selection.selected.length;
    const numRows = this.dataSource.data.length;
    return numSelected === numRows;
  }

  /** Selects all rows if they are not all selected; otherwise clear selection. */
  masterToggle() {
    this.isAllSelected() ?
      this.selection.clear() :
      this.dataSource.data.forEach(row => this.selection.select(row));
  }

  ngOnInit() {
    this.dataSource = new LeagueDataSource(this.leagueService);
    this.dataSource.loadLeagues('ASC', 0, 5);
  }

  ngAfterViewInit() {
    this.paginator.page
      .pipe(
        tap(() => {
          this.loadLeaguesPage();
          this.selection.clear();
        })
      )
      .subscribe();
  }

  loadLeaguesPage() {
    this.dataSource.loadLeagues(
      'asc',
      this.paginator.pageIndex,
      this.paginator.pageSize);
  }

  defineLeagues() {
    let lgs = ([]);
    this.dataSource.data.forEach(row => this.selection.isSelected(row) ? lgs.push(row['id']) : '');
    
    if (lgs.length > 0) {
      this.mktService.setLeagues(lgs)
        .subscribe(
          () => {
            this.snackBar.open('Ligas definidas!', '', { duration: 5000 });            
          },
          err => {
            this.snackBar.open('Erro ao definir as ligas!', '', { duration: 5000 });
          }

        );
    }

  }

}
