import {AccountClass} from "./accounts/AccountClass";
import {UserClass} from "./users/UserClass";
import {Order} from "./orders/order.class";
import {OrderItem} from "./orders/order-item.class";
import { Injectable } from '@angular/core';

export class ProductModel
{
  private m_selectOrderId:number = 0 ;
  private m_selectedOrder:Order = new Order ;
  private m_listOrderItems:OrderItem[] = [] ;

  public ProductModel(): void {}
  loadModel(): void
  {

  }
  storeModel(): void
  {
    // localStorage.setItem( "userId", this.selectedUserId.toString()) ;
    // localStorage.setItem( "accountId", this.selectAccountId.toString()) ;
    // localStorage.setItem("baseRoute", "/user-accounts/"+ this.selectedUserId ) ;
    // localStorage.setItem( "accounts-view", this.selectedView )
  }
  loadUserModel( userid :number )
  {
    // this.selectedUserId = userid ;
    // localStorage.setItem( "userId", userid.toString( )) ;
    // this.loadModel() ;
  }

}


@Injectable({
  providedIn: 'root',
})
export class OrderModel
{
  private m_selectOrderId:number = 0 ;
  private m_selectedOrder:Order = new Order ;
  private m_listOrderItems:OrderItem[] = [] ;

  public OrderModel(): void {}
  loadModel(): void
  {

  }
}

@Injectable({
  providedIn: 'root',
})
export class Model
{
  private m_baseRoute:string = "" ;
  private m_baseView = "" ;

  private m_accountList:AccountClass[] = [] ;
  private m_subAccountList:AccountClass[] = [] ;

  private m_user:UserClass = new UserClass() ;
  private m_selectAccountId:number = 0 ;
  private m_selectedUserId:number = 0 ;
  private m_selectedView:string = "summary" ;

  private m_primaryAccountId:number = 0 ;
  private m_secondaryAccountId:number = 0 ;

  public Model() {}

  loadModel(): void
  {
    this.selectAccountId = Number(localStorage.getItem("accountId" )) ;
    this.selectedUserId = Number(localStorage.getItem("userId")) ;
    this.baseRoute =  localStorage.getItem("baseRoute") ;
    this.selectedView = localStorage.getItem( "accounts-view" ) ;
    this.loadUser() ;
    this.loadAccounts() ;
  }
  loadUser() : void
  {

  }
  loadAccounts() : void
  {

  }
  storeModel(): void
  {
    localStorage.setItem( "userId", this.selectedUserId.toString()) ;
    localStorage.setItem( "accountId", this.selectAccountId.toString()) ;
    localStorage.setItem("baseRoute", "/main/user-accounts/"+ this.selectedUserId ) ;
    localStorage.setItem( "accounts-view", this.selectedView )


  }
  loadUserModel( userid :number )
  {
    this.selectedUserId = userid ;
    localStorage.setItem( "userId", userid.toString( )) ;
    this.loadModel() ;
   }

  get baseRoute(): string { return this.m_baseRoute; }
  set baseRoute(value: string | null ) { this.m_baseRoute = value == null ? "" : value ; }

  get accountList(): AccountClass[] { return this.m_accountList; }
  set accountList(value: AccountClass[]) { this.m_accountList = value; }


  get subAccountList(): AccountClass[] { return this.m_subAccountList; }
  set subAccountList(value: AccountClass[]) { this.m_subAccountList = value; }

  get user():UserClass { return this.m_user; }
  set user( value: UserClass ) { this.m_user = value; }

  get selectAccountId(): number { return this.m_selectAccountId;}
  set selectAccountId(value: number | null ) { this.m_selectAccountId = value == null ? 0 : value ;  }

  get primaryAccountId(): number { return this.m_primaryAccountId;}
  set primaryAccountId(value: number | null )
  {
    this.m_primaryAccountId = value == null ? 0 : value ;
  }

  get secondaryAccountId(): number { return this.m_secondaryAccountId;}
  set secondaryAccountId(value: number | null ) { this.m_secondaryAccountId = value == null ? 0 : value ;  }

  get selectedUserId():number { return this.m_selectedUserId; }
  set selectedUserId( value:number ) { this.m_selectedUserId = value; }

  get selectedView():string { return this.m_selectedView; }
  set selectedView( value:string | null ) { this.m_selectedView = ( value == null ? "summary" : value ) ; }

  get baseView():string { return this.m_baseView; }
  set baseView( value:string | null ) { this.m_baseView = ( value == null ? "summary" : value ) ; }
  //private setSelectedUserId(userid: number) { this.m_selectedUserId = userid ; }
}
