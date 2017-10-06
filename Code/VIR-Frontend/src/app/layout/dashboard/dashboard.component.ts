import { Component, OnInit } from '@angular/core';
import { routerTransition } from '../../router.animations';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.scss'],
    animations: [routerTransition()]
})
export class DashboardComponent implements OnInit {
    public alerts: Array<any> = [];
    public sliders: Array<any> = [];

    constructor() {
        this.sliders.push({
            imagePath: 'assets/images/11.jpg',
            label: 'Vocabulary in Reading',
            text: 'Analyzing one word at a time.'
        }, {
            imagePath: 'assets/images/22.jpg',
            label: 'Statistics and Words',
            text: 'Detailed information and data representation of the word lists.'
        }, {
            imagePath: 'assets/images/33.jpg',
            label: 'Explore more',
            text: 'Information about the word is only a click away. '
        });
    }

    ngOnInit() {
    }

    public closeAlert(alert: any) {
        const index: number = this.alerts.indexOf(alert);
        this.alerts.splice(index, 1);
    }
}
