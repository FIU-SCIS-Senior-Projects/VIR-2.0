import { Component, OnInit } from '@angular/core';
import { TextService, IText } from '../../shared'
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { DecimalPipe } from '@angular/common';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';

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
  showDiv: boolean;
  textLVL: string;
  textColor: string;
  showOnlyIcons: boolean;
  backLabel: string = TextStatisticsComponent.BACK_LABEL;
  enhanceTextLabel: string = TextStatisticsComponent.ENHANCETEXT_LABEL;

  awlPercentage: number;
  hiPercentage: number;
  medPercentage: number;
  lowPercentage: number;
  noCategoryPercentage: number;

  closeResult: string;

  constructor(private _textService: TextService, public router: Router, private _location: Location, private modalService: NgbModal) { }

  ngOnInit() {
    window.scrollTo(0, 0);
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

  /*
    80-100: Beginner Lever
    70-79: Intermediate Level
    60-69: Upper intermediate Level
    30-59: Advanced Level
    0--29: College Level
  */
  textLevel(score: number) {
    if (score <= 29 && score >= 1) {
      this.textLVL = 'College Level';
      this.textColor = 'red';
    } else if (score <= 59 && score >= 30) {
      this.textLVL = 'Advanced Level';
      this.textColor = 'orange';
    } else if (score <= 69 && score >= 60) {
      this.textLVL = 'Upper intermediate Level';
      this.textColor = 'yellow';
    } else if (score <= 79 && score >= 70) {
      this.textLVL = 'Intermediate Level';
      this.textColor = 'green';
    } else if (score === 0) {
      this.textLVL = 'it is not applicable for texts under 100 words.'
    } else {
      this.textLVL = 'Beginner Lever';
      this.textColor = 'blue';
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

  // events for Pie Chart
  public chartClicked(e: any): void {
    console.log(e);
  }

  public chartHovered(e: any): void {
    console.log(e);
  }

  // Update Pie chart
  updatePieChart(awl: number, hi: number, med: number, low: number, noCategory: number) {
    this.pieChartData = [awl, hi, med, low, noCategory]
  }

}
