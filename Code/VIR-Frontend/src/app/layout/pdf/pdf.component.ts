import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@Component({
  selector: 'app-pdf',
  templateUrl: './pdf.component.html',
  styleUrls: ['./pdf.component.scss'],
  animations: [routerTransition()]
  
})
export class PdfComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

}
