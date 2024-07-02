import {inject} from '@angular/core';
import {
  CanActivateFn, CanMatchFn,
  Router, UrlTree
} from '@angular/router';

import { SecurityLogindataComponent } from "./security-logindata/security-logindata.component"

export const authGuard: CanMatchFn|CanActivateFn = () => {
  const logindata = inject(SecurityLogindataComponent);
  const router = inject(Router);

  if (logindata.loginToken) {
    return true;
  }
  // temporarily authenticate all
  else return true ;

  // Redirect to the login page
  return router.parseUrl('/signin');
};
