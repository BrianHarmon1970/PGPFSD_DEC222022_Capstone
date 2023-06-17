import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { SecuritySigninComponent } from './security/security-signin/security-signin.component';
import { SecuritySignupComponent } from './security/security-signup/security-signup.component';
import { PortfolioLandingComponent } from './portfolio-landing/portfolio-landing.component';
import { MainPortfolioComponent } from './main/main-portfolio/main-portfolio.component';
import { MainComponent } from './main/main.component';
//import { MainPortfolioComponent } from './main/main-portfolio/main-portfolio.component';

import { MainRoutingRoutingModule } from './main-routing/main-routing-routing.module';
import { SecurityLogoutComponent } from './security/security-logout/security-logout.component';
import { authGuard } from './security/security-authguard';
import {DashboardComponent} from "./dashboard/dashboard.component";
import {EditUserComponent} from "./edit-user/edit-user.component";
import {UserdetailsComponent} from "./userdetails/userdetails.component";
import {UserlistComponent} from "./userlist/userlist.component";


const routes: Routes = [
  //{path:'',component:AppComponent},
  {"path":"users",component:UserlistComponent},
  {"path":"details/:id",component:UserdetailsComponent},
  {"path":"edit/:id",component:EditUserComponent},
  {"path":"dashboard",component:DashboardComponent} ,

  {path:'',component:PortfolioLandingComponent},
  {path:'signin', component:SecuritySigninComponent},
  {path:'signup', component:SecuritySignupComponent},
  {path:'logout', component:SecurityLogoutComponent},
  {path:'portfolio_landing', component:PortfolioLandingComponent},
  {path:'portfolio', component:MainPortfolioComponent },
  {path:'main', component:MainComponent, canActivate: [authGuard]},
  {path:"**", component:PortfolioLandingComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes),
    MainRoutingRoutingModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
