import { Injectable, Inject } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { IPage } from '../interface'
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/map';

@Injectable()
export class WordsListService {


  constructor(private http: HttpClient) {
  }

  getData(page: number, category: string, size: number, sort: string): Observable<IPage> {
    return this.http.get<IPage>(`/api/words?category=${category}&page=${page}&size=${size}&sortKey=value&sortDirection=${sort}`)
      .do((res => console.log(res)));
  }
}


