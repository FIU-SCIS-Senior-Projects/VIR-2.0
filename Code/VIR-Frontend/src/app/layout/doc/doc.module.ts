import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DocRoutingModule } from './doc-routing.module';
import { DocComponent } from './doc.component';

@NgModule({
  imports: [
    CommonModule,
    DocRoutingModule
  ],
  declarations: [DocComponent]
})
export class DocModule { }
