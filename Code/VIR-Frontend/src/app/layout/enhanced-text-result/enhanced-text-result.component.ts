import { Component, OnInit } from '@angular/core';
import { TextService, IText, IWord } from '../../shared'
import { Router } from '@angular/router';


@Component({
  selector: 'app-enhanced-text-result',
  templateUrl: './enhanced-text-result.component.html',
  styleUrls: ['./enhanced-text-result.component.scss']
})
export class EnhancedTextResultComponent implements OnInit {

  text: IText;

  constructor(private _textService: TextService, public router: Router) { }

  ngOnInit() {
    this.text = this._textService.resultText;
  }

}
