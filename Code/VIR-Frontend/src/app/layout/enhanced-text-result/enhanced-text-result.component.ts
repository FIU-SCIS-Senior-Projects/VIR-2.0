import { Component, OnInit } from '@angular/core';
import { TextService, IText, IWord } from '../../shared'
import { Router } from '@angular/router';


@Component({
  selector: 'app-enhanced-text-result',
  templateUrl: './enhanced-text-result.component.html',
  styleUrls: ['./enhanced-text-result.component.scss']
})
export class EnhancedTextResultComponent implements OnInit {

  public static BACK_LABEL: string = ' Back';
  public static readonly STATISTICS_LABEL: string = ' Statistics';

  text: IText;
  showOnlyIcons: boolean;
  backLabel: string = EnhancedTextResultComponent.BACK_LABEL;
  statisticsLabel: string = EnhancedTextResultComponent.STATISTICS_LABEL;

  constructor(private _textService: TextService, public router: Router) { }

  ngOnInit() {
    this.showOnlyIcons = window.innerWidth <= 572;
    this.updaTeLabels();
    this.text = this._textService.resultText;
  }

  redirectBack(): void {
    this.router.navigateByUrl('/dashboard');
  }

  onResize(event) {
    this.showOnlyIcons = window.innerWidth <= 572;
    this.updaTeLabels();
    event.target.innerWidth;
  }

  private updaTeLabels(): void {
    this.backLabel = this.showOnlyIcons ?  '' : EnhancedTextResultComponent.BACK_LABEL;
    this.statisticsLabel = this.showOnlyIcons ?  '' : EnhancedTextResultComponent.STATISTICS_LABEL;
  }

}
