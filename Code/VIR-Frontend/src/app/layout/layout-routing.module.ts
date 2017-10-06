import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LayoutComponent } from './layout.component';

const routes: Routes = [
    {
        path: '', component: LayoutComponent,
        children: [
            { path: 'dashboard', loadChildren: './dashboard/dashboard.module#DashboardModule' },
            { path: 'blank-page', loadChildren: './blank-page/blank-page.module#BlankPageModule' },
            {path : 'text', loadChildren: './text/text.module#TextModule' },
            {path : 'doc', loadChildren: './doc/doc.module#DocModule' },
            {path : 'pdf', loadChildren: './pdf/pdf.module#PdfModule' },
            {path : 'image', loadChildren: './image/image.module#ImageModule' },
            {path : 'enhanced-text-result', loadChildren: './enhanced-text-result/enhanced-text-result.module#EnhancedTextResultModule' },
            {path : 'text-statistics', loadChildren: './text-statistics/text-statistics.module#TextStatisticsModule' },
        ]
    }

];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class LayoutRoutingModule { }
