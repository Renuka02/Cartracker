import { Component, OnInit } from '@angular/core';
import { CarTrackerService } from '../../services/car-tracker.service';

@Component({
  selector: 'app-content5',
  templateUrl: './content5.component.html',
  styleUrls: ['./content5.component.css']
})
export class Content5Component implements OnInit {
  allVehicles:Vehicles[];
  vehicleData:Vehicle;
  allHistoricalAlerts:Alerts[];
  showDetails:boolean;

  constructor(private vehicleService:CarTrackerService) {
    sessionStorage.setItem("currentContent","5");
    this.showDetails = false;
    this.vehicleData = {
      vin:"",
      make:"",
      model:"",
      year:0,
      redlineRpm:0,
      maxFuelVolume:0,
      lastServiceDate:""
    };
  }

  ngOnInit() {
    this.vehicleService.getHighAlerts().subscribe((vehicles) => {
      this.allVehicles = vehicles;
    });
  }

  onSubmitClicked(_vin:string){
    console.log("_vin"+" : "+_vin);

    if(_vin == undefined){
      alert("Vehicle selection is mandatory");
    }
    else{
      this.vehicleService.getVehicleDetails(_vin).subscribe((data) => {
        this.vehicleData = data;
      });

      this.vehicleService.getHistoricalAlerts(_vin).subscribe((readings) => {
        this.allHistoricalAlerts = readings;
      });
      this.showDetails = true;
    }
  }

}

interface Vehicles{
  vin:string,
  make:string,
  model:string
}

interface Vehicle{
  vin:string,
  make:string,
  model:string,
  year:number,
  redlineRpm:number,
  maxFuelVolume:number,
  lastServiceDate:string
}

interface Alerts{
  fuelVolume:number,
  checkEngineLightOn:boolean,
  engineCoolantLow:boolean,
  engineRpm:string,
  tirefrontleft:number,
  tirefrontright:number,
  tireleftleft:number,
  tireleftright:number,
  alert_priority:string
}