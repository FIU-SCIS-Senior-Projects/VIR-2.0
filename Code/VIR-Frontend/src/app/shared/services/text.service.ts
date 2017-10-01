import { Injectable, Inject } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { IText, IWord, IColor } from '../interface'
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/map';

@Injectable()
export class TextService {

  constructor(private http: HttpClient) { }

  //TextService store the result from text component into resultText so later on can be passed to enhancedTextResultPage
  public resultText: IText;

  //TextService store the chosen colors from text component into chosenColor so later on can be passed to enhancedTextResultPage
  public chosenColors: IColor;

  enhancedText(textArea: string): Observable<IText> {
    return this.http.post<IText>('/api/analyzeText', textArea)
      .do((res => console.log(res)));
  }

}


