import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home/home.component';
import {IssueComponent} from "./issue/issue.component";
import {GroupComponent} from "./group/group.component";
import {AdminComponent} from "./admin/admin.component";
import {ManageGroupComponent} from "./manage-group/manage-group.component";
import {RegistrationComponent} from "./registration/registration.component";

const routes: Routes = [
  {path:"",redirectTo:"login",pathMatch:"full"},
  {path:"login",component:LoginComponent},
  {path:"home",component:HomeComponent},
  {path:"issue",component:IssueComponent},
  {path:"group",component:GroupComponent},
  {path:"admin",component:AdminComponent},
  {path:"manage",component:ManageGroupComponent},
  {path:"registration", component: RegistrationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
