import { Injectable, Inject } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { IText, IWord } from '../interface'


@Injectable()
export class TextService {

  text: IText;

  constructor(private http: HttpClient) { }

  enhancedText(textArea: string): void {
    // Make the HTTP request:
    this.http.post<IText>('http://localhost:8080/api/analyzeText', textArea)
      .subscribe
      (res => {
        console.log(res);
        this.text = res;
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

}
