import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { TextService, IText, IWordMatch, IDefinition, DefinitionService } from '../../shared'
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

  definitionBox: boolean;
  processing: boolean;
  turnOn: boolean;
  wordDefinition: IDefinition;
  text: IText;
  error: boolean;
  showOnlyIcons: boolean;
  backLabel: string = EnhancedTextResultComponent.BACK_LABEL;
  statisticsLabel: string = EnhancedTextResultComponent.STATISTICS_LABEL;
  cleanWord: string;
  definitionDiv: any;

  // tslint:disable-next-line:max-line-length
  constructor(private _textService: TextService, public _definitionService: DefinitionService, public router: Router, private _location: Location) { }

  ngOnInit() {
    this.turnOn = false;
    this.showOnlyIcons = window.innerWidth <= 680;
    this.updaTeLabels();
    this.text = this._textService.resultText;
    this.getDefinition('book')
    this.definitionBox = false;
  }

  // it gets the definition of  the word using DefinitionService
  getDefinition(word: string) {
    this.processing = true;
    this.definitionBox = true;
    this.error = false;
    this.cleanWord = word.replace(/[^a-zA-Z ]/g, '');
    this._definitionService.getDefinitionService(this.cleanWord)
      .subscribe
      (res => {
        this.wordDefinition = res;
        this.turnOn = true;
        this.processing = false;
      },
      (err: HttpErrorResponse) => {
        if (err.error instanceof Error) {
          console.log('Client-side Error occured');
        } else {
          this.error = true;
          this.processing = false;
          this.definitionBox = false;
          console.log('Server-side Error occured');
        }
      }
      );

  }


  // it hides the definition box
  hideDefinitionBox() {
    this.definitionBox = false;
  }

  // return to the previous page
  backClicked() {
    this._location.back();
  }

  onResize(event) {
    this.showOnlyIcons = window.innerWidth <= 680;
    this.updaTeLabels();
    event.target.innerWidth;
  }

  // scrolling to the top on both the window and definition Div
  scrollToTop() {
    window.scrollTo(0, 0);
    this.definitionDiv = document.getElementById('definition');
    this.definitionDiv.scrollTop = 0;
  }

  private updaTeLabels(): void {
    this.backLabel = this.showOnlyIcons ? '' : EnhancedTextResultComponent.BACK_LABEL;
    this.statisticsLabel = this.showOnlyIcons ? '' : EnhancedTextResultComponent.STATISTICS_LABEL;
  }

}
