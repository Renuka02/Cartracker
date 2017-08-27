import { Component, OnInit } from '@angular/core';
import { CarTrackerService } from '../../services/car-tracker.service';

@Component({
  selector: 'app-content4',
  templateUrl: './content4.component.html',
  styleUrls: ['./content4.component.css']
})
export class Content4Component implements OnInit {

  locations:Locations[];
  firstLoc:Locations;
  lat:number = 40.785091;
  lng:number = -73.968285;
  allVehicles : Vehicles[];

  constructor(private vehicleService:CarTrackerService) {
    sessionStorage.setItem("currentContent","4");
    this.firstLoc = {
      vin :"1HGCR2F3XFA027534",
      latitude: 40.785091,
      longitude:-73.968285,
      time_stamp: "dafDAfdaf"
    };
  }

  ngOnInit() {

    this.vehicleService.getHighAlerts().subscribe((vehicles) => {
      this.allVehicles = vehicles;
    });
    
  }


  onSubmitClicked(_vin:string){
    this.vehicleService.getGeoLocation(_vin).subscribe((locations1)=>{
      this.locations = locations1;
      // console.log(this.locations[0].latitude); 
      //console.log(this.firstLoc); 
      this.lat = this.locations[0].latitude;
      this.lng = this.locations[0].longitude;
    });
  }

}



interface Vehicles{
  vin:string,
  make:string,
  model:string
}

interface Locations{
  vin : string,
  latitude : number,
  longitude:number,
  time_stamp:string
}