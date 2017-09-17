import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ImageRoutingModule } from './image-routing.module';
import { ImageComponent } from './image.component';

@NgModule({
  imports: [
    CommonModule,
    ImageRoutingModule
  ],
  declarations: [ImageComponent]
})
export class ImageModule { }
