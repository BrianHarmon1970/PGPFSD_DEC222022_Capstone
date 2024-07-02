import { Component, OnInit } from '@angular/core';
import { Route, Router } from '@angular/router';
import { DataService } from '../data.service';
import { UserClass } from '../UserClass';

@Component({
  selector: 'app-userlist',
  templateUrl: './userlist.component.html',
  styleUrls: ['./userlist.component.css']
})
export class UserlistComponent implements OnInit {

  constructor(private router:Router, private service:DataService) { }
  users:UserClass[] = [] ;


  ngOnInit(): void {
    this.service.getAllUser().subscribe(result=>this.users=result);
  }
  UserAccountList( id:number)
  {
     this.setRoute( null, "main/user-accounts/" + id.toString() )
  }
  setRoute( acctid:number | null, routing:string ):void
  {
    acctid = acctid == null ? 0 : acctid ;
    //let id:string = acctid.toString() ;
    //localStorage.setItem("accountId", id )
    this.router.navigateByUrl( '/', { skipLocationChange: true }).then(() => {
    });

    this.router.navigate([routing]);
    //window.location.reload() ;
  }
  DeleteUserById(id:number){
    //refresh the list once user is deleted
    this.users=this.users.filter(c=>c.id!=id);
    //delete user
    this.service.deleteById(id);
    console.log("user Deleted");
  }

}
