import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppComponent } from 'src/app/app.component';
import { SecurityLogindataComponent } from '../security-logindata/security-logindata.component';

@Component({
  selector: 'app-security-logout',
  templateUrl: './security-logout.component.html',
  styleUrls: ['./security-logout.component.css']
})
export class SecurityLogoutComponent implements OnInit 
{
  router:Router;
  app:AppComponent ;
  loginData: SecurityLogindataComponent ;
  
  constructor( app:AppComponent, 
                router:Router,
                loginData:SecurityLogindataComponent ) 
  { 
    this.app = app ;
    this.router = router ;
    this.loginData = loginData ;
  }
  ngOnInit(): void {  }
  Logout():void
  {
    this.loginData.loginToken = false ;
    this.app.selectAppNavbar() ;
    this.router.navigate(['/']) ;
  }

}
