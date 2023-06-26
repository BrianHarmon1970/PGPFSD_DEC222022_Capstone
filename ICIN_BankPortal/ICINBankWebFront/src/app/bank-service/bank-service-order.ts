export class BankServiceOrder
{
  // service order class specific values
  ID:number | null = null ; // actually general for any record type - not specifically service order but business level object
  Type:string |  null = null ;

  // used for withdraw and depost - transaction created as created and the id passed to the BankService to be processed from there
  txId:number | null = null ;

  // create account or account record values
  accountID:number | null = null ;
  userID:number | null = null ;
  accountClass:string | null = null ;
  accountType:string | null = null ;
  accountName:string | null = null ;
  accountNumber:string | null = null ;
  accountBalance:number |  null = null ;
  accountClassTypeTag:string | null = null ; // pseudo column indicating the desired classtype id to be derived by order process
}
