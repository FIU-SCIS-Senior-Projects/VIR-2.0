import { Observable } from 'rxjs/Observable';
import { Component, Input, NgModule, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { TextService, WordsListService, IText, IWord, IStatistics } from '../../shared'
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

  constructor(private _wordsList: WordsListService) {
    this.defaultPagination = 1;
    this.advancedPagination = 1;
    this.paginationSize = 1;
    this.disabledPagination = 1;
    this.isDisabled = true;
   }

  getAWLWordList(pageNumber: number): void {
    this.defaultPagination = 1;
    this.awlPagination = true;
    this.hiPagination = false;
    this.medPagination = false;
    this.lowPagination = false;
    this._wordsList.getAWL(pageNumber)
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

  getHIWordList(pageNumber: number): void {
    this.defaultPagination = 1;
    this.awlPagination = false;
    this.hiPagination = true;
    this.medPagination = false;
    this.lowPagination = false;
    this._wordsList.getHI(pageNumber)
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


  getMedWordList(pageNumber: number): void {
    this.defaultPagination = 1;
    this.awlPagination = false;
    this.hiPagination = false;
    this.medPagination = true;
    this.lowPagination = false;
    this._wordsList.getMed(pageNumber)
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


  getLowWordList(pageNumber: number): void {
    this.defaultPagination = 1;
    this.awlPagination = false;
    this.hiPagination = false;
    this.medPagination = false;
    this.lowPagination = true;
    this._wordsList.getLow(pageNumber)
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




  ngOnInit() {
    this.getAWLWordList(this.awlpage);
  }

}
