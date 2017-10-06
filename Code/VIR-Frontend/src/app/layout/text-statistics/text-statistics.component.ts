import { Component, OnInit } from '@angular/core';
import { TextService, IText, IWord, IStatistics } from '../../shared'
import { Router } from '@angular/router';

@Component({
  selector: 'app-text-statistics',
  templateUrl: './text-statistics.component.html',
  styleUrls: ['./text-statistics.component.scss']
})
export class TextStatisticsComponent implements OnInit {

 text: IText;
 statistics: IStatistics;

  constructor(private _textService: TextService, public router: Router) { }

  ngOnInit() {
    this.text = this._textService.resultText;
    this.statistics = this._textService.resultStatistic;
  }

}
