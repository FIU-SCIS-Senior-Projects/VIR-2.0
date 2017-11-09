import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BlankPageRoutingModule } from './blank-page-routing.module';
import { BlankPageComponent } from './blank-page.component';


@NgModule({
  imports: [
    CommonModule,
    NgbModule.forRoot(),
    BlankPageRoutingModule
  ],
  declarations: [BlankPageComponent]
})
export class BlankPageModule { }
