import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'security-logindata',
  templateUrl: './security-logindata.component.html',
  styleUrls: ['./security-logindata.component.css']
})
export class SecurityLogindataComponent 
{
  public loginToken:boolean = false ; // simulated dummy login token
  public loginName:string = '' ; 
  public password:string =  '' ;
  public password_confirmation = '' ;
}
