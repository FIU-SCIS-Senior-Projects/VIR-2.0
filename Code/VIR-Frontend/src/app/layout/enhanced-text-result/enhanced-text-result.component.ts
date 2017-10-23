import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { TextService, IText, IWord, IDefinition, DefinitionService } from '../../shared'
import { Router } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-enhanced-text-result',
  templateUrl: './enhanced-text-result.component.html',
  styleUrls: ['./enhanced-text-result.component.scss']
})
export class EnhancedTextResultComponent implements OnInit {

  public static BACK_LABEL: string = ' Back';
  public static readonly STATISTICS_LABEL: string = ' Statistics';


  turnOn: boolean;
  wordDefinition: IDefinition;
  text: IText;
  showOnlyIcons: boolean;
  backLabel: string = EnhancedTextResultComponent.BACK_LABEL;
  statisticsLabel: string = EnhancedTextResultComponent.STATISTICS_LABEL;

  // tslint:disable-next-line:max-line-length
  constructor(private _textService: TextService, public _definitionService: DefinitionService, public router: Router, private _location: Location) { }

  ngOnInit() {
    this.getDefinition('book')
    this.showOnlyIcons = window.innerWidth <= 680;
    this.updaTeLabels();
    this.text = this._textService.resultText;
  }

  getDefinition(word: string) {
    this.turnOn = false;
    this._definitionService.getDefinitionService(word)
      .subscribe
      (res => {
        this.wordDefinition = res;
        this.turnOn = true;
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

  backClicked() {
    this._location.back();
  }

  onResize(event) {
    this.showOnlyIcons = window.innerWidth <= 680;
    this.updaTeLabels();
    event.target.innerWidth;
  }

  private updaTeLabels(): void {
    this.backLabel = this.showOnlyIcons ? '' : EnhancedTextResultComponent.BACK_LABEL;
    this.statisticsLabel = this.showOnlyIcons ? '' : EnhancedTextResultComponent.STATISTICS_LABEL;
  }

}
