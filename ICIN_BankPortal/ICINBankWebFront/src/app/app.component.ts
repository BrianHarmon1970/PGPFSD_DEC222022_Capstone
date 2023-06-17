import { Component } from '@angular/core';
import { NgIf } from '@angular/common';
import { MainNavbarComponent } from './main/main-navbar/main-navbar.component';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Portfolio';
  public navContext:string = "app" ; // 'app' or 'main' for app-navbar or main-navbar selection
  selectAppNavbar():void { this.navContext = "app" ; }
  selectMainNavbar():void { this.navContext = "main" ; }
}
