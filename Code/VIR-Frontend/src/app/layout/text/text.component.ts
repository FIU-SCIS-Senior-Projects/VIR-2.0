import { Component, OnInit, Input, NgModule } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { TextService } from '../../shared'

@Component({
  selector: 'app-text',
  templateUrl: './text.component.html',
  styleUrls: ['./text.component.scss'],
  animations: [routerTransition()]

})
export class TextComponent implements OnInit {

  constructor(private _textService: TextService) { }

  @Input() textArea: string;

  enhancedText2(): void {
    this._textService.enhancedText(this.textArea)
  }

  ngOnInit() {
  }


}
