import { Component, OnInit } from '@angular/core';
import {AccountClass} from "../AccountClass";
import {ActivatedRoute, Router} from "@angular/router";
import {AccountsService} from "../accounts.service";
import {Model} from "../../model.class";

@Component({
  selector: 'app-user-accounts',
  templateUrl: './user-accounts.component.html',
  styleUrls: ['./user-accounts.component.css']
})
export class UserAccountsComponent implements OnInit {
  accounts:AccountClass[] = [] ;
  selectedAcctId:number | null = null  ;
  feature!:string | null ;
  userId:string | null = null ;
  userAccountsModel:Model = new Model ;
  constructor(
    private router:Router,
    private activatedRoute:ActivatedRoute,
    private service:AccountsService ) {  }

  ngOnInit(): void
  {
    this.userId = this.activatedRoute.snapshot.paramMap.get("userid") ;
    const userid = this.activatedRoute.snapshot.paramMap.get("userid") ;
    //this.userid = this.userid == null ? "summary" : this.userid ;
    localStorage.setItem("baseRoute", "/user-accounts/"+ this.userId )

    this.feature = localStorage.getItem("accounts-feature") ;
    this.feature = this.feature == null ? "summary" : this.feature ;

    this.selectedAcctId = Number(localStorage.getItem("accountId" )) ;
    //this.selectedAcctId = this.selectedAcctId == null ? "" : this.selectedAcctId ;
    this.service.getAllAccountForUser(Number(userid)).subscribe(result=>this.accounts=result);

    localStorage.setItem("baseRoute", "/user-accounts/"+ this.userId ) ;
    localStorage.setItem("baseRoute", "/user-accounts/"+ this.userId ) ;
    this.selectedAcctId = Number(localStorage.getItem("accountId" )) ;

    this.userAccountsModel.selectedUserId = Number(this.userId)  ;
    this.userAccountsModel.loadModel() ;
    
  }
  showEdit():boolean { return false  ;}

  accountDeposit( acctid:number | null, routing:string) : void
  {
    this.setFeature( "deposit" ) ;
    this.setRoute( acctid, routing ) ;
  }
  accountWithdraw( acctid:number | null, routing:string) : void
  {
    this.setFeature( "withdraw" ) ;
    this.setRoute( acctid, routing ) ;
  }
  accountSummary( acctid:number | null, routing:string ) : void
  {
    //this.feature = "summary" ;
    localStorage.setItem("accounts-feature", "summary" ) ;
    this.setRoute( acctid, routing ) ;
  }

  accountEdit( acctid:number | null, routing:string ) : void
  {
    //this.feature = "edit" ;
    localStorage.setItem("accounts-feature", "edit" ) ;
    this.setRoute( acctid, routing ) ;
  }
  setFeatureRoute( feature:string | null,
                   acctid:number | null,
                   route:string ) : void
  {
    this.setFeature( feature ) ;
    this.setRoute( acctid, route ) ;
  }

  setFeature( feature:string | null )
  {
    feature = feature == null ? "summary" : feature ;
    localStorage.setItem("accounts-feature", feature ) ;
  }
  setRoute( acctid:number | null, routing:string ):void
  {
    acctid = acctid == null ? 0 : acctid ;
    let id:string = acctid.toString() ;
    localStorage.setItem("accountId", id )
    this.router.navigateByUrl('/', { skipLocationChange: true }).then(() => {

    });
    this.router.navigate([routing]);
    window.location.reload() ;
      //this.router.navigate(["/account-summary/0"])

    //this.router.navigate([routing])
  //  this.router.navigateByUrl("/") ;
  //  this.router.navigateByUrl( routing ) ;

    // this.router.navigateByUrl("/") ;
    //        this.router.navigate([`/${routing}`])
  }
  DeleteAccountById(id:number | null ){
    //refresh the list once user is deleted
    this.accounts=this.accounts.filter(c=>c.id!=id);
    //delete user
    this.service.deleteById(id);
    console.log("Account Deleted");
  }
}
