import { Component, Inject, OnInit } from '@angular/core';
import { AppComponent } from 'src/app/app.component';
import { SecurityLogoutComponent } from 'src/app/security/security-logout/security-logout.component';
import { SecurityModule } from 'src/app/security/security.module';
import { Router } from '@angular/router';

@Component({
  selector: 'main-navbar',
  templateUrl: './main-navbar.component.html',
  styleUrls: ['./main-navbar.component.css']
})
export class MainNavbarComponent implements OnInit {

  //app:AppComponent ;
  logoutComponent:SecurityLogoutComponent  ;
  
  

  constructor( logoutComponent:SecurityLogoutComponent ) 
  { 
    this.logoutComponent = logoutComponent ;
    //this.app = app ;
    //this.router = router ;
  }

  ngOnInit(): void {
  }
  logOut():void 
  {
    this.logoutComponent.Logout() ;
    
   
  }

}
