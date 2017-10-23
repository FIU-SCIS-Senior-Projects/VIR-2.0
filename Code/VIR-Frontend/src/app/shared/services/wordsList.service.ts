import { Injectable, Inject } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { IText, IWord} from '../interface'
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/map';

@Injectable()
export class WordsListService {


  public resultText: IText;

  constructor(private http: HttpClient) {
  }


  getAWL(page: number): Observable<IText> {
    return this.http.get<IText>('http://localhost:4200/api/words?category=awl&page=' + page + '&size=20&sortKey=value&sortDirection=ASC')
      .do((res => console.log(res)));
  }

  getHI(page: number): Observable<IText> {
    return this.http.get<IText>('http://localhost:4200/api/words?category=hi&page=' + page + '&size=20&sortKey=value&sortDirection=ASC')
      .do((res => console.log(res)));
  }


  getMed(page: number): Observable<IText> {
    return this.http.get<IText>('http://localhost:4200/api/words?category=med&page=' + page + '&size=20&sortKey=value&sortDirection=ASC')
      .do((res => console.log(res)));
  }

  getLow(page: number): Observable<IText> {
    return this.http.get<IText>('http://localhost:4200/api/words?category=low&page=' + page + '&size=20&sortKey=value&sortDirection=ASC')
      .do((res => console.log(res)));
  }

}


