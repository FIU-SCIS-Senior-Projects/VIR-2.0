import { Injectable, Inject } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { IText, IWordMatch} from '../interface'
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/map';

@Injectable()
export class WordsListService {

  public resultText: IText;

  constructor(private http: HttpClient) {
  }

  getData(page: number, category: string): Observable<IText> {
    return this.http.get<IText>(`/api/words?category=${category}&page=${page}&size=20&sortKey=value&sortDirection=ASC`)
      .do((res => console.log(res)));
  }
}


