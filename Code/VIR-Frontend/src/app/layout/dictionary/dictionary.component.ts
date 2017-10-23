import { Observable } from 'rxjs/Observable';
import { Component, Input, NgModule, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { TextService, WordsListService, IText, IWordMatch, IStatistics } from '../../shared'
import { Router } from '@angular/router';
import { NgbPaginationConfig } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-dictionary',
  templateUrl: './dictionary.component.html',
  styleUrls: ['./dictionary.component.scss'],
  providers: [NgbPaginationConfig]
})
export class DictionaryComponent implements OnInit {
  text: IText;
  statistics: IStatistics;
  processing: boolean;
  error: boolean;
  turnOn: boolean;
  awlPagination: boolean;
  hiPagination: boolean;
  medPagination: boolean;
  lowPagination: boolean;
  awlpage = 1;
  hipage = 1;
  medpage = 1;
  lowpage = 1;

  defaultPagination: number;
  advancedPagination: number;
  paginationSize: number;
  disabledPagination: number;
  isDisabled: boolean;

  activeCategory: string

  constructor(private _wordsList: WordsListService) {
    this.defaultPagination = 1;
    this.advancedPagination = 1;
    this.paginationSize = 1;
    this.disabledPagination = 1;
    this.isDisabled = true;

    this.activeCategory = 'awl';
   }

  updateCategory(category: string) {
    this.activeCategory = category;
    this.getWordList(0, this.activeCategory);
  }
  
  private getWordList(pageNumber: number, category: string): void {
    this.defaultPagination = 1;
    this.awlPagination = (category === 'awl');
    this.hiPagination = (category === 'hi');
    this.medPagination = (category === 'med');
    this.lowPagination = (category === 'low');
    this._wordsList.getData(pageNumber, category)
      .subscribe
      (res => {
        this.text = res;
        this.turnOn = true;
      },
      (err: HttpErrorResponse) => {
        if (err.error instanceof Error) {
          console.log('Client-side Error occured');
        } else {
          this.error = true;
          this.processing = false;
          console.log('Server-side Error occured');
        }
      }
      );
  }

  getAWLWordList(pageNumber: number): void {
    this.getWordList(pageNumber, 'awl')
  }

  getHIWordList(pageNumber: number): void {
    this.getWordList(pageNumber, 'hi')
  }

  getMedWordList(pageNumber: number): void {
    this.getWordList(pageNumber, 'med')
  }

  getLowWordList(pageNumber: number): void {
    this.getWordList(pageNumber, 'low')
  }

  ngOnInit() {
    this.getWordList(this.awlpage, this.activeCategory);
  }

}
