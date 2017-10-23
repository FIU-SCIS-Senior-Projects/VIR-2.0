import { Injectable, Inject } from '@angular/core';
import { Http, Response, Headers } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { IText, IWordMatch, IDefinition} from '../interface'
import 'rxjs/add/operator/catch';
import 'rxjs/add/operator/do';
import 'rxjs/add/observable/throw';
import 'rxjs/add/operator/map';

@Injectable()
export class DefinitionService {


  public resultText: IText;

  constructor(private http: HttpClient) {
  }


  getDefinitionService(word: string): Observable<IDefinition> {
    return this.http.get<IDefinition>('/api/entries/' + word + '?source=WIKI')
      .do((res => console.log(res)));
  }

}
