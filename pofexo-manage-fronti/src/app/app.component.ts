import { MediaMatcher } from '@angular/cdk/layout';
import { ChangeDetectorRef, Component, OnDestroy } from '@angular/core';
import { AuthenticationService } from './services/authentication.service';
import { TeamService } from './services/team.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  mobileQuery: MediaQueryList;

  private _mobileQueryListener: () => void;

  private logado = false;
  private budget = 0;

  constructor(
    private authService: AuthenticationService,
    private teamService: TeamService,
    changeDetectorRef: ChangeDetectorRef,
    media: MediaMatcher
  ) {
    this.mobileQuery = media.matchMedia('(max-width: 600px)');
    this._mobileQueryListener = () => changeDetectorRef.detectChanges();
    this.mobileQuery.addListener(this._mobileQueryListener);
  }

  ngOnDestroy(): void {
    this.mobileQuery.removeListener(this._mobileQueryListener);
  }

  ngOnInit(): void {
    this.authService.sucessfull.subscribe(item => this.logado = item);
    this.teamService.budgetEvent.subscribe(value => this.budget = value);
  }

  getLogado() {
    return this.logado;
  }

  getBudget() {
    return this.budget;
  }
}
