import { Component, OnInit } from '@angular/core';
import { CarTrackerService } from '../../services/car-tracker.service';

@Component({
  selector: 'app-content1',
  templateUrl: './content1.component.html',
  styleUrls: ['./content1.component.css']
})
export class Content1Component implements OnInit {
  vehicles:Vehicles[];

  constructor(private vehicleService:CarTrackerService) {
    sessionStorage.setItem("currentContent","1");
  }

  ngOnInit() {
    this.vehicleService.getVehicles().subscribe((vehicles) => {
      this.vehicles = vehicles;
    });
  }

}

interface Vehicles{
  vin:string,
  make:string,
  model:string,
  year:number,
  redlineRpm:number,
  maxFuelVolume:number,
  lastServiceDate:string
}