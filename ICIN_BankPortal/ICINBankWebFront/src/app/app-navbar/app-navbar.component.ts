import { Component, OnInit } from '@angular/core';
import { AppComponent } from '../app.component';
import { AppModule } from '../app.module';

@Component({
  selector: 'app-app-navbar',
  templateUrl: './app-navbar.component.html',
  styleUrls: ['./app-navbar.component.css']
})
export class AppNavbarComponent implements OnInit {

  parent:AppComponent ;
  constructor(app:AppComponent ) 
  { 
    this.parent = app ;
  }

  ngOnInit(): void {

  }
  setContext(): void 
  {
    this.parent.navContext = "main" ;
  }

}
