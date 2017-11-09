import { DefinitionService } from './../../shared/services/definition.service';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { EnhancedTextResultRoutingModule } from './enhanced-text-result-routing.module';
import { EnhancedTextResultComponent } from './enhanced-text-result.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

@NgModule({
  imports: [
    CommonModule,
    EnhancedTextResultRoutingModule,
    NgbModule.forRoot(),
    FormsModule
  ],
  declarations: [EnhancedTextResultComponent],
  providers: [DefinitionService],
})
export class EnhancedTextResultModule { }
