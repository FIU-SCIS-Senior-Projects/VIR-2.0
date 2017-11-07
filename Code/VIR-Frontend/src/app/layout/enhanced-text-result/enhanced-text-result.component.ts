import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { TextService, IText, IWordMatch, IDefinition, DefinitionService } from '../../shared'
import { Router } from '@angular/router';
import { Location } from '@angular/common';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-enhanced-text-result',
  templateUrl: './enhanced-text-result.component.html',
  styleUrls: ['./enhanced-text-result.component.scss']
})
export class EnhancedTextResultComponent implements OnInit {

  public static BACK_LABEL: string = ' Back';
  public static readonly STATISTICS_LABEL: string = ' Statistics';


  processing: boolean;
  wordDefinition: IDefinition;
  text: IText;
  error: boolean;
  showOnlyIcons: boolean;
  backLabel: string = EnhancedTextResultComponent.BACK_LABEL;
  statisticsLabel: string = EnhancedTextResultComponent.STATISTICS_LABEL;
  cleanWord: string;
  closeResult: string;
  category: string;
  wordCategory: string;


  // tslint:disable-next-line:max-line-length
  constructor(private _textService: TextService, public _definitionService: DefinitionService, public router: Router, private _location: Location,
    private modalService: NgbModal) { }

  ngOnInit() {
    window.scrollTo(0, 0);
    this.showOnlyIcons = window.innerWidth <= 680;
    this.updaTeLabels();
    this.text = this._textService.resultText;
    this.getDefinition('book')
  }

  // it gets the definition of  the word using DefinitionService
  getDefinition(word: string) {
    this.processing = true;
    this.error = false;
    this.cleanWord = word.replace(/[^a-zA-Z ]/g, '');
    this._definitionService.getDefinitionService(this.cleanWord)
      .subscribe
      (res => {
        this.wordDefinition = res;
        this.processing = false;
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


  // return to the previous page
  backClicked() {
    this._location.back();
  }

  onResize(event) {
    this.showOnlyIcons = window.innerWidth <= 680;
    this.updaTeLabels();
    event.target.innerWidth;
  }


  private updaTeLabels(): void {
    this.backLabel = this.showOnlyIcons ? '' : EnhancedTextResultComponent.BACK_LABEL;
    this.statisticsLabel = this.showOnlyIcons ? '' : EnhancedTextResultComponent.STATISTICS_LABEL;
  }

  // definiton Model open
  open(content) {
    this.modalService.open(content).result.then((result) => {
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = `Dismissed ${this.getDismissReason(reason)}`;
    });
  }

  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

  // Convert the active category name "hi,med,low,..." to "High Frequency,.." used for popup title.
  activeCategory(cat: string) {
      this.category = cat;
      // tslint:disable-next-line:max-line-length
      this.wordCategory = ( cat === 'awl') ? 'AWL' : ( cat === 'hi') ? 'High Frequency' : ( cat === 'med') ? 'Medium Frequency' : ( cat === 'low') ? 'Low Frequency' : 'Names & Off-Lists';
  }
}
