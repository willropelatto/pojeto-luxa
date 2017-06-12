import { ToastrService } from 'ngx-toastr';
import { Injectable } from '@angular/core';
import { Router, NavigationStart } from '@angular/router';
import { Observable } from 'rxjs';
import { Subject } from 'rxjs/Subject';
 
@Injectable()
export class AlertService {
    private subject = new Subject<any>();
    private keepAfterNavigationChange = false;
 
    constructor(private router: Router,
                private toast: ToastrService) {
        // clear alert message on route change
        router.events.subscribe(event => {
            if (event instanceof NavigationStart) {
                if (this.keepAfterNavigationChange) {
                    // only keep for a single location change
                    this.keepAfterNavigationChange = false;
                } else {
                    // clear alert
                    this.subject.next();
                }
            }
        });
    }
 
    success(message: string, keepAfterNavigationChange = false) {
        this.toast.success(message);    
    }
 
    error(message: string, keepAfterNavigationChange = false) {
        this.toast.error(message);
    }
 
    getMessage(): Observable<any> {
        return this.subject.asObservable();
    }
}