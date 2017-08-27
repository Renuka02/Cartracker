import { Component, OnInit } from '@angular/core';
import { CarTrackerService } from '../../services/car-tracker.service';

@Component({
  selector: 'app-content2',
  templateUrl: './content2.component.html',
  styleUrls: ['./content2.component.css']
})
export class Content2Component implements OnInit {
  vehicles:Vehicles[];
  
  constructor(private vehicleService:CarTrackerService) {
    sessionStorage.setItem("currentContent","2");
  }

  ngOnInit() {
    this.vehicleService.getHighAlerts().subscribe((vehicles) => {
      this.vehicles = vehicles;
    });
  }

}

interface Vehicles{
  vin:string,
  make:string,
  model:string,
  alertCount:number
}