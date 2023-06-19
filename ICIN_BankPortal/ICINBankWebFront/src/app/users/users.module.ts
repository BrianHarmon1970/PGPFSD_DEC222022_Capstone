import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {UserlistComponent} from "./userlist/userlist.component";
import {UserdetailsComponent} from "./userdetails/userdetails.component";
import {EditUserComponent} from "./edit-user/edit-user.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {RouterModule} from "@angular/router";



@NgModule({
  declarations: [
    UserlistComponent,
    UserdetailsComponent,
    EditUserComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule
  ],
  exports: [ UserlistComponent, UserdetailsComponent, EditUserComponent ]
})
export class UsersModule { }
