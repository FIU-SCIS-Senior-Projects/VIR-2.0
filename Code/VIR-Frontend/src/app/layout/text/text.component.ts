import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@Component({
  selector: 'app-text',
  templateUrl: './text.component.html',
  styleUrls: ['./text.component.scss'],
  animations: [routerTransition()]
  
})
export class TextComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
