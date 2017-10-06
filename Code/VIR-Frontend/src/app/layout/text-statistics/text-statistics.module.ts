import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { TextStatisticsRoutingModule } from './text-statistics-routing.module';
import { TextStatisticsComponent } from './text-statistics.component';


@NgModule({
  imports: [
    CommonModule,
    TextStatisticsRoutingModule,
    FormsModule
  ],
  declarations: [TextStatisticsComponent]
})
export class TextStatisticsModule { }
