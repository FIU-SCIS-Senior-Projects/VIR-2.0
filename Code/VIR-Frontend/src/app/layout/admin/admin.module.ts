import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AdminRoutingModule } from './admin-routing.module';
import { AdminComponent } from './admin.component';


@NgModule({
  imports: [
    CommonModule,
    NgbModule.forRoot(),
    AdminRoutingModule
  ],
  declarations: [AdminComponent]
})
export class  AdminModule { }
