import {ChangeDetectorRef, Component, NgZone, OnInit} from '@angular/core';
import {AccountClass} from "../AccountClass";
import {ActivatedRoute, Router} from "@angular/router";
import {AccountsService} from "../accounts.service";
import {Model} from "../../model.class";
import {SecurityLogindataComponent} from "../../security/security-logindata/security-logindata.component";
import {AppComponent} from "../../app.component";


@Component({
  selector: 'app-user-accounts',
  templateUrl: './user-accounts.component.html',
  styleUrls: ['./user-accounts.component.css']
})
export class UserAccountsComponent implements OnInit {
  accounts:AccountClass[] = [] ;
  //selectedAcctId:number | null = null  ;
//  feature!:string | null ;
  userId:string | null = null ;
  //userAccountsModel:Model = new Model ;

  constructor(
    private ref:ChangeDetectorRef,
    private router:Router,
    private activatedRoute:ActivatedRoute,
    private service:AccountsService,
    public userAccountsModel:Model,
    protected app:AppComponent,
    private loginData:SecurityLogindataComponent)
  {
    this.userId = ( this.activatedRoute.snapshot.paramMap.get("userid") == null ?
      this.userAccountsModel.user.id.toString() :
      this.activatedRoute.snapshot.paramMap.get("userid")) ;
    const userid = this.userId ;

    this.userAccountsModel.user.id = Number(this.userId) ;
    this.userAccountsModel.baseRoute = "/main/user-accounts/" ;
    this.userAccountsModel.baseView = "summary" ;
  }

  ngOnInit(): void
  {
    this.userId = this.activatedRoute.snapshot.paramMap.get("userid") ;
    const userid = this.activatedRoute.snapshot.paramMap.get("userid") ;
    this.service.getAllAccountForUser(Number(this.userId)).subscribe(result=>this.accounts=result);
  }

  // showEdit():boolean { return false  ;}
  accountDeposit( ) : void
  {
    this.setView( "deposit" ) ;
    this.setRoute( this.userAccountsModel.selectAccountId,
      this.userAccountsModel.baseRoute ) ;
  }
  accountWithdraw() : void
  {
    this.setView( "withdraw" ) ;
    this.setRoute( this.userAccountsModel.selectAccountId,
      this.userAccountsModel.baseRoute ) ;
  }
  checkbookRequest() : void
  {
    this.setView( "checkbook" ) ;
    // this.setRoute( this.userAccountsModel.selectAccountId,
    //   this.userAccountsModel.baseRoute ) ;
     //this.setRoute( this.userAccountsModel.selectAccountId,
      //'main/product-list/' ) ;
  }
  accountSummary(  ) : void
  {
    this.ref.detectChanges();
    this.setView("") ;
    this.ref.detectChanges();

    this.setView( "summary" ) ;
    this.ref.detectChanges();

    this.setRoute( this.userAccountsModel.selectAccountId,
      this.userAccountsModel.baseRoute ) ;

    this.ref.detectChanges();
//    console.log( "accountSummary", this.userAccountsModel.selectAccountId ) ;

  }
  accountRegister() : void
  {
    this.setView( "register" ) ;
    this.setRoute( this.userAccountsModel.selectAccountId,
      this.userAccountsModel.baseRoute ) ;
  }
  accountTransfer() : void
  {
    this.setView( "transfer" ) ;
    this.setRoute( this.userAccountsModel.selectAccountId,
      this.userAccountsModel.baseRoute ) ;
  }
  accountEdit() : void
  {
    this.setView( "edit" ) ;
    this.setRoute( this.userAccountsModel.selectAccountId,
      this.userAccountsModel.baseRoute ) ;
  }
  accountSelect( acctid:number | null )
  {
    this.userAccountsModel.selectAccountId = acctid ;
    //this.selectedAcctId= acctid ;
    let account:AccountClass = new AccountClass() ;
    let masterAccount:AccountClass = new AccountClass() ;
    let subs:AccountClass[] = [] ;

    this.service.getAccountById( acctid ).subscribe(account=>account=account) ;
    this.service.getMasterAccountById( acctid ).subscribe(masterAccount=>masterAccount=masterAccount) ;

    this.userAccountsModel.primaryAccountId = null ;
    this.userAccountsModel.secondaryAccountId = null ;
    if ( account.accountClass == "PRIMARY" ) {

      this.userAccountsModel.primaryAccountId = masterAccount.id;
      this.service.getSubAccountsById(acctid).subscribe(subs => subs = subs);
      this.userAccountsModel.subAccountList = subs ;
    }
    else if ( account.accountClass == "SECONDARY" )
    {
      this.userAccountsModel.secondaryAccountId = account.id ;
    }

    this.setRoute( this.userAccountsModel.selectAccountId,
      this.userAccountsModel.baseRoute ) ;
  }
  setViewRoute( view:string | null,
                   acctid:number | null,
                   route:string ) : void
  {
    this.setView( view ) ;
    this.setRoute( acctid, route ) ;
  }

  setView( view:string | null )
  {
    this.userAccountsModel.selectedView = view ;
   // this.app.accountsModel.selectedView = view ;
    this.ref.detectChanges();
    this.ref.detectChanges();
  }
  setRoute( acctid:number | null, routing:string ):void
  {
    acctid = acctid == null ? 0 : acctid ;
    let id:string = acctid.toString() ;
    //this.userAccountsModel.selectAccountId = Number(id) ;
    //this.userAccountsModel.storeModel() ;
    //this.app.accountsModel.selectAccountId = Number(id) ;
    //this.app.accountsModel = this.userAccountsModel ;
    //this.router.navigateByUrl('/main/', { skipLocationChange: true }).then(() => {});

    console.log( "route:" + routing ) ;

    //this.router.navigate([routing]);
    //this.router.navigate([this.userAccountsModel.baseRoute]);
    //window.location.reload()
    //this.userAccountsModel.loadModel() ;
  }

  DeleteAccountById(id:number | null ){
    //refresh the list once user is deleted
    this.accounts=this.accounts.filter(c=>c.id!=id);
    //delete user
    this.service.deleteById(id);
    console.log("Account Deleted");
  }
}
