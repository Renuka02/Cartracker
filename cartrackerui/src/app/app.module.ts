import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { BootstrapGridModule } from 'ng2-bootstrap-grid';
import { HttpModule } from '@angular/http';
import {FormsModule} from '@angular/forms'
import {RouterModule , Routes} from '@angular/router'
import {AgmCoreModule} from '@agm/core'
import {NgxChartsModule} from '@swimlane/ngx-charts';

import { AppComponent } from './app.component';
import { MenuBarComponent } from './components/menu-bar/menu-bar.component';
import { Content1Component } from './components/content1/content1.component';
import { Content2Component } from './components/content2/content2.component';
import { Content3Component } from './components/content3/content3.component';
import { Content4Component } from './components/content4/content4.component';
import { Content5Component } from './components/content5/content5.component';

import { CarTrackerService } from './services/car-tracker.service';

const appRoutes:Routes = [
  {
    path:'',
    component:Content1Component
  },
  {
    path:'highalerts',
    component:Content2Component
  },
  {
    path:'vehiclehistory',
    component:Content3Component
  },
  {
    path:'vehiclegeolocation',
    component:Content4Component
  },
  {
    path:'vehiclehistoricalalerts',
    component:Content5Component
  }
]


@NgModule({
  declarations: [
    AppComponent,
    MenuBarComponent,
    Content1Component,
    Content2Component,
    Content3Component,
    Content4Component,
    Content5Component,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    BootstrapGridModule,
    HttpModule,
    FormsModule,
    NgxChartsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyBbkXab1eQCpG6MfJi26FXAYPkNnQ-lEv8'
    }),
    RouterModule.forRoot(appRoutes)
  ],
  providers: [
    CarTrackerService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {

}
