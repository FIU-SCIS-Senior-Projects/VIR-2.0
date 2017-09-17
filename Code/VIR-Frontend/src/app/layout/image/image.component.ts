import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@Component({
  selector: 'app-image',
  templateUrl: './image.component.html',
  styleUrls: ['./image.component.scss'],
  animations: [routerTransition()]
  
})
export class ImageComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
