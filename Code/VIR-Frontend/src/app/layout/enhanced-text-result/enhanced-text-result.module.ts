import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { EnhancedTextResultRoutingModule } from './enhanced-text-result-routing.module';
import { EnhancedTextResultComponent } from './enhanced-text-result.component';


@NgModule({
  imports: [
    CommonModule,
    EnhancedTextResultRoutingModule,
    FormsModule
  ],
  declarations: [EnhancedTextResultComponent]
})
export class EnhancedTextResultModule { }
