import {AccountClass} from "./accounts/AccountClass";
import {UserClass} from "./users/UserClass";

export class Model
{
  private m_baseRoute:string = "" ;
  private m_accountList:AccountClass[] = [] ;
  private m_user:UserClass = new UserClass() ;
  private m_selectAccountId:number = 0 ;
  private m_selectedUserId:number = 0 ;

  public Model() {}

  loadModel(): void {}
  storeModel(): void {}

  get baseRoute(): string { return this.m_baseRoute; }
  set baseRoute(value: string) { this.m_baseRoute = value; }

  get accountList(): AccountClass[] { return this.m_accountList; }
  set accountList(value: AccountClass[]) { this.m_accountList = value; }

  get user():UserClass { return this.m_user; }
  set user( value: UserClass ) { this.m_user = value; }

  get selectAccountId(): number { return this.m_selectAccountId;}
  set selectAccountId(value: number) {    this.m_selectAccountId = value;  }

  get selectedUserId(): number { return this.m_selectedUserId; }
  set selectedUserId(value: number) { this.m_selectedUserId = value; }
}
