import { Component, OnInit } from '@angular/core';
import { TextService, IText, IWordMatch, IStatistics, IWordCount } from '../../shared'
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { DecimalPipe } from '@angular/common';

@Component({
  selector: 'app-text-statistics',
  templateUrl: './text-statistics.component.html',
  styleUrls: ['./text-statistics.component.scss']
})
export class TextStatisticsComponent implements OnInit {

  public static BACK_LABEL = ' Back';
  public static readonly ENHANCETEXT_LABEL: string = ' Enhanced Text';
  public pieChartLabels: string[] = ['AWL', 'High Freq.', 'Medium Freq.', 'Low Freq.', 'No Category'];
  public pieChartData: number[];
  public pieChartType = 'pie';

  text: IText;
  statistics: IStatistics;
  wordCount: IWordCount;
  showDiv: boolean;
  textLVL: string;
  showOnlyIcons: boolean;
  backLabel: string = TextStatisticsComponent.BACK_LABEL;
  enhanceTextLabel: string = TextStatisticsComponent.ENHANCETEXT_LABEL;
  height = 30;

  constructor(private _textService: TextService, public router: Router, private _location: Location) { }

  ngOnInit() {
    this.text = this._textService.resultText;
    if (!this.text) {
      return;
    } else {
      this.textLevel(this.text.fleschReadingScore);
    }
    this.showDiv = true;
    this.showOnlyIcons = window.innerWidth <= 680;
    this.updaTeLabels();
    if (!this.text) {
      return;
    } else {
      this.updatePieChart(this.text.statistics.wordCount.awl, this.text.statistics.wordCount.hi,
        this.text.statistics.wordCount.med, this.text.statistics.wordCount.low, this.text.statistics.wordCount.noCategory);
    }
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

  onResize(event) {
    this.showOnlyIcons = window.innerWidth <= 680;
    this.updaTeLabels();
    event.target.innerWidth;
  }

  private updaTeLabels(): void {
    this.backLabel = this.showOnlyIcons ? '' : TextStatisticsComponent.BACK_LABEL;
    this.enhanceTextLabel = this.showOnlyIcons ? '' : TextStatisticsComponent.ENHANCETEXT_LABEL;
  }

   // events
   public chartClicked(e: any): void {
    console.log(e);
  }

  public chartHovered(e: any): void {
    console.log(e);
  }

  updatePieChart(awl: number, hi: number, med: number, low: number, noCategory: number) {
    this.pieChartData = [awl, hi, med, low, noCategory]
  }

}
