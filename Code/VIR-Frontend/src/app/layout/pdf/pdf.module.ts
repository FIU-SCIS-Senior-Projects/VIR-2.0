import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PdfRoutingModule } from './pdf-routing.module';
import { PdfComponent } from './pdf.component';

@NgModule({
  imports: [
    CommonModule,
    PdfRoutingModule
  ],
  declarations: [PdfComponent]
})
export class PdfModule { }
