import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private router: Router) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
      if (localStorage.getItem('token')) {
          return true;
      }

      this.router.navigate(['/login'], { queryParams: { returnUrl: state.url }});
      return false;
  }

}
