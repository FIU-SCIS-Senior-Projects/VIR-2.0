import { Component, OnInit, Input, NgModule } from '@angular/core';
import { IWord, AdminService } from '../../shared'
import { Router } from '@angular/router';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {
  currentJustify = 'start';
  processing = false;
  error = false;
  errorAdd = false;
  word: IWord;
  showTable = false;

  @Input() searchArea: string;
  @Input() wordArea: string;
  @Input() idArea: number;

  addWordMessage: string;
  editWordMessage: string;
  deleteWordMessage: string;
  alertWord: string;

  categoryItems: string[] = ['Category...', 'awl', 'hi', 'med', 'low'];
  category: string = this.categoryItems[0];

  sessionHistory: string[] = [];
  index = 1;

  constructor(private _admin: AdminService, public router: Router) { }

  // search the word in database
  searchWord(): void {
    this.processing = true;
    this.error = false;
    this.errorAdd = false;
    this.alertWord = this.searchArea;
    this._admin.getWord(this.searchArea)
      .subscribe
      (res => {
        this.word = res;
        this.processing = false;
        this.showTable = true;
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

  // Add new word to data base
  addWord(): void {
    this.processing = true;
    this.error = false;
    this.errorAdd = false;
    this._admin.postWord(this.wordArea, this.category)
      .subscribe
      (res => {
        this.processing = false;
        this.sessionHistory[this.index] = this.wordArea + ' is added to ' + this.category + ' category.'
        this.index++;
      },
      (err: HttpErrorResponse) => {
        if (err.error instanceof Error) {
          console.log('Client-side Error occured');
        } else {
          this.errorAdd = true;
          this.processing = false;
          console.log('Server-side Error occured');
        }
      }
      );
  }


  // Add new word to data base
  editWord(): void {
    this.processing = true;
    this.error = false;
    this.errorAdd = false;
    this._admin.putWord(this.wordArea, this.category, this.idArea)
      .subscribe
      (res => {
        this.processing = false;
        this.sessionHistory[this.index] = 'Word ID: ' + this.idArea + ' was edited to ' + this.wordArea;
        this.index++;
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

  // Delete the word in database
  deleteWord(): void {
    this.processing = true;
    this.error = false;
    this.errorAdd = false;
    this._admin.deleteWord(this.wordArea)
      .subscribe
      (res => {
        this.processing = false;
        this.sessionHistory[this.index] = this.wordArea + ' was succesfully erased from database.'
        this.index++;
      },
      (err: HttpErrorResponse) => {
        if (err.error instanceof Error) {
          console.log('Client-side Error occured');
        } else {
          // this.error = true;
          this.processing = false;
          console.log('Server-side Error occured');
        }
      }
      );
  }

  ngOnInit() {
  }

}
