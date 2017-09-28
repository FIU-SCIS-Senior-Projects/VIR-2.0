import { Component, OnInit, Input, NgModule } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { TextService, IText, IWord  } from '../../shared'
import { Router } from '@angular/router';

@Component({
  selector: 'app-text',
  templateUrl: './text.component.html',
  styleUrls: ['./text.component.scss'],
  animations: [routerTransition()],
})
export class TextComponent implements OnInit {

  text: IText;
  constructor(private _textService: TextService, public router: Router) { }
  
  @Input() textArea: string;

  enhancedText2(): void {
    this._textService.enhancedText(this.textArea)
    .subscribe
      (res => {
        this.text = res;
        this._textService.resultText = this.text;
        this.router.navigateByUrl('/enhanced-text-result');
      },
      (err: HttpErrorResponse) => {
        if (err.error instanceof Error) {
          console.log('Client-side Error occured');
        } else {
          console.log('Server-side Error occured');
        }
      }
      );
  }

  ngOnInit() {
  }


}


