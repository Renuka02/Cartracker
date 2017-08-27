import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-menu-bar',
  templateUrl: './menu-bar.component.html',
  styleUrls: ['./menu-bar.component.css']
})
export class MenuBarComponent implements OnInit {
  show=parseInt(sessionStorage.getItem("currentContent"));
  cols:Columns[];

  constructor() {
    this.cols=[
      { link:"/", label:"Vehicle Details" },
      { link:"highalerts", label:"High Alerts" },
      { link:"vehiclehistory", label:"Plot Vehicle History" },
      { link:"vehiclegeolocation", label:"Vehicle Geolocation" },
      { link:"vehiclehistoricalalerts", label:"Vehicle Historical Alerts" }
    ]
   }

  ngOnInit() {

  }

  changeContent(contentNumber){
    this.show=contentNumber;
  }

}

interface Columns{
  link:string;
  label:string;
}