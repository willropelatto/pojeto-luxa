import { AlertService } from './../../util/alert.service';
import { PlayerFilter } from './../shared/playerFilter.model';
import { LeagueService } from './../../league/shared/league.service';
import { League } from './../../league/league.model';
import { FormControl } from '@angular/forms';
import { PlayerService } from './../../player/shared/player.service';
import { Player } from './../../player/shared/player.model';
/**
 * Componente de cadastro de transfermarkets.
 *
 * @author Márcio Casale de Souza <contato@kazale.com>
 * @since 0.0.3
 */

import { Component, ViewChild } from '@angular/core';
import { OnInit } from '@angular/core';
import { Router, NavigationExtras } from '@angular/router';

import { SelectComponent } from 'ng2-select';

import { Transfermarket, TransfermarketService } from '../shared';

@Component({
    selector: 'kz-transfermarket-cadastrar',
    templateUrl: './transfermarket-cadastrar.component.html',
    styleUrls: ['./transfermarket-cadastrar.component.css']
})
export class TransfermarketCadastrarComponent implements OnInit {

    private transfermarket: Transfermarket;
    public players: Array<Player> = [];
    public leagues: Array<League> = [];
    public playerFilter: PlayerFilter = new PlayerFilter();
    private value: any = {};
    selectedValue: string;



    public startPrice: number = 0;
    public endPrice: number = 0;
    public rating: number = 0;

    playerCtrl: FormControl;
    filteredPlayers: any;

    positionsCtrl: FormControl;
    filteredPositions: any;

    leaguesCtrl: FormControl;
    filteredLeagues: any;
    positions = ['CAM', 'CB', 'CDM', 'CF', 'CM', 'GK', 'LB', 'LM', 'LW', 'LWB', 'RB', 'RM', 'RW', 'RWB', 'ST'];



	/**
	 * Construtor.
	 *
	 * @param Router router
	 * @param TransfermarketService transfermarketService
	 */
    constructor(
        private router: Router,
        private transfermarketService: TransfermarketService,
        private playerService: PlayerService,
        private leagueService: LeagueService,
        private alertService: AlertService) {

        this.playerCtrl = new FormControl();
        this.filteredPlayers = this.playerCtrl.valueChanges
            .startWith(null)
            .map(player => this.filterPlayers(player));

        this.positionsCtrl = new FormControl();
        this.filteredPositions = this.positionsCtrl.valueChanges
            .startWith(null)
            .map(position => this.filterPositions(position));

        this.leaguesCtrl = new FormControl();
        this.filteredLeagues = this.leaguesCtrl.valueChanges
            .startWith(null)
            .map(leagues => this.filterLeagues(leagues));


    }

	/**
	 * Método executado logo após a criação do componente.
	 */
    ngOnInit() {
        this.transfermarket = new Transfermarket();
        this.playerService.listarTodos()
            .subscribe((players) => {
                this.players = players;
            });
        this.leagueService.listarTodos()
            .subscribe((leagues) => {
                this.leagues = leagues;
            });
        this.playerFilter.name = '';
        this.playerFilter.position = '';
        this.playerFilter.endValue = 0;
        this.playerFilter.startValue = 0;
        this.playerFilter.rating = 0;
        this.playerFilter.league = '';

    }

    filterPlayers(val: string) {
        return val ? this.players.filter(s => new RegExp(`^${val}`, 'gi').test(s.name))
            : this.players;
    }

    filterPositions(val: string) {
        return val ? this.positions.filter(s => new RegExp(`^${val}`, 'gi').test(s))
            : this.positions;
    }

    filterLeagues(val: string) {
        return val ? this.leagues.filter(s => new RegExp(`^${val}`, 'gi').test(s.name))
            : this.leagues;
    }

    onInputStartPrice(event: any) {
        this.playerFilter.startValue = event.value;
    }

    onInputEndPrice(event: any) {
        this.playerFilter.endValue = event.value;
    }

    onInputRating(event: any) {
        this.playerFilter.rating = event.value;
    }


    register() {

    }


    check(filter: PlayerFilter): boolean {
        console.log(filter.name);
        console.log(filter.position);
        console.log(filter.rating);

        if ((filter.name.length === 0) &&
            (filter.position.length === 0) &&
            (filter.rating === 0)) {
            return false;
        } else {
            return true;
        }
    }


    //https://plnkr.co/edit/?p=preview

    /**
     * Método responsável por cadastrar um novo transfermarket.
     */
    cadastrar() {
        //this.transfermarket.id = new Date().getTime();
        this.transfermarketService.cadastrar(this.transfermarket);
        this.router.navigate(['/transfermarkets']);
    }


    autoTicks = false;
    disabled = false;
    invert = false;
    max = 15000;
    min = 0;
    showTicks = false;
    step = 100;
    thumbLabel = false;
    defaultvalue = 0;
    vertical = false;

    get tickInterval(): number | 'auto' {
        return this.showTicks ? (this.autoTicks ? 'auto' : this._tickInterval) : null;
    }
    set tickInterval(v) {
        this._tickInterval = Number(v);
    }
    private _tickInterval = 1;


    filter(filterPlayer: PlayerFilter): NavigationExtras {
        let navextras: NavigationExtras = {
            queryParams: { "playerFilter": JSON.stringify(filterPlayer) }
        };
        return navextras;

    }

    onFilter(filterPlayer: PlayerFilter) {
        if (this.check(filterPlayer)) {
            /*  if (filterPlayer.name = ''){
      
              }*/
            this.router.navigate(['/transfermarkets/filtrar'], this.filter(filterPlayer));
        } else {
            this.alertService.error('Informe pelo menos um filtro!');
        }

    }






}