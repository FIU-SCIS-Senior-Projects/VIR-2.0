import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TextRoutingModule } from './text-routing.module';
import { TextComponent } from './text.component';

@NgModule({
  imports: [
    CommonModule,
    TextRoutingModule
  ],
  declarations: [TextComponent]
})
export class TextModule { }
