import { Component, OnInit } from '@angular/core';
import { TextService, IText, IWordMatch, IStatistics } from '../../shared'
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { DecimalPipe } from '@angular/common';

@Component({
  selector: 'app-text-statistics',
  templateUrl: './text-statistics.component.html',
  styleUrls: ['./text-statistics.component.scss']
})
export class TextStatisticsComponent implements OnInit {

  text: IText;
  statistics: IStatistics;
  showDiv: boolean;
  textLVL: string;
  e = 2.718281828459045;

  constructor(private _textService: TextService, public router: Router, private _location: Location) { }

  ngOnInit() {
    this.text = this._textService.resultText;
    if (!this.text) {
      return;
    } else {
      this.textLevel(this.text.fleschReadingScore);
    }
    this.showDiv = true;
  }

  textLevel(score: number) {
    if (score <= 30 && score >= 1) {
      this.textLVL = 'Hard';
    } else if (score <= 60 && score > 30) {
      this.textLVL = 'Medium';
    } else if (score === 0) {
      this.textLVL = 'it not applicable for texts under 100 words '
    } else {
      this.textLVL = 'Easy';
    }
  }

  backClicked() {
    this._location.back();
  }

}
