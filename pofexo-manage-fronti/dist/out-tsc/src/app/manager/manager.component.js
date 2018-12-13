var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __metadata = (this && this.__metadata) || function (k, v) {
    if (typeof Reflect === "object" && typeof Reflect.metadata === "function") return Reflect.metadata(k, v);
};
import { Component, Input } from '@angular/core';
import { Manager } from '../beans/manager';
import { ActivatedRoute, Router } from '@angular/router';
import { ManagerService } from '../services/manager.service';
import { first } from 'rxjs/operators';
import { MatSnackBar } from '@angular/material';
import { AuthenticationService } from '../services/authentication.service';
var ManagerComponent = /** @class */ (function () {
    function ManagerComponent(managerService, authService, route, router, snackBar) {
        this.managerService = managerService;
        this.authService = authService;
        this.route = route;
        this.router = router;
        this.snackBar = snackBar;
        this.currentUser = this.authService.getCurrentUser();
        console.log(this.currentUser);
        if (this.currentUser != undefined) {
            console.log('aeeee');
        }
        else {
            console.log('nops');
        }
    }
    ManagerComponent.prototype.getManager = function () {
        var _this = this;
        var id = +this.route.snapshot.paramMap.get('code');
        if (id > 0) {
            this.managerService.getManager(id)
                .subscribe(function (luxa) {
                return _this.luxa = luxa;
            });
        }
        else {
            this.luxa = new Manager();
        }
    };
    ManagerComponent.prototype.newManager = function (tele) {
        var _this = this;
        this.managerService.addManager(tele).pipe(first())
            .subscribe(function (luxa) {
            _this.luxa = luxa;
            _this.snackBar.open('Usuário registrado com sucesso.', 'OK', { duration: 3000 });
            _this.router.navigate(['/login']);
        }, function (error) {
            _this.snackBar.open('Não foi possivel registrar o usuário. ' + error, '', { duration: 3000 });
        });
    };
    ManagerComponent.prototype.ngOnInit = function () {
        this.getManager();
    };
    __decorate([
        Input(),
        __metadata("design:type", Manager)
    ], ManagerComponent.prototype, "luxa", void 0);
    ManagerComponent = __decorate([
        Component({
            selector: 'app-manager',
            templateUrl: './manager.component.html',
            styleUrls: ['./manager.component.css']
        }),
        __metadata("design:paramtypes", [ManagerService,
            AuthenticationService,
            ActivatedRoute,
            Router,
            MatSnackBar])
    ], ManagerComponent);
    return ManagerComponent;
}());
export { ManagerComponent };
//# sourceMappingURL=manager.component.js.map