import { Component, OnInit } from '@angular/core';
 
import { AlertService } from './../util/alert.service';

import { ToastrService } from 'ngx-toastr';

 
@Component({
    selector: 'alert',
    templateUrl: 'alert.component.html' 
})
 
export class AlertComponent {
    message: any;
 
    constructor(public alertService: AlertService,
                public toastrService: ToastrService) { }
 
    ngOnInit() {

    }

    onSucess(message: string){
        this.toastrService.success(message);
    }

    onError(message: string){
        this.toastrService.error(message);
    }
}