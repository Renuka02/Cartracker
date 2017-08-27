import { Component, OnInit } from '@angular/core';
import { CarTrackerService } from '../../services/car-tracker.service';

@Component({
  selector: 'app-content3',
  templateUrl: './content3.component.html',
  styleUrls: ['./content3.component.css']
})
export class Content3Component implements OnInit {
  cols:string[];
  showgraph = false;
  showgraph2d = false;
  view: any[] = [1000, 300];


  multi : any[] = [
    {
      "name": "Germany",
      "series": [
        {
          "name": "2010",
          "value": 7300000
        },
        {
          "name": "2011",
          "value": 8940000
        }
      ]
    },
  
    {
      "name": "USA",
      "series": [
        {
          "name": "2010",
          "value": 7870000
        },
        {
          "name": "2011",
          "value": 8270000
        }
      ]
    },
  
    {
      "name": "France",
      "series": [
        {
          "name": "2010",
          "value": 5000002
        },
        {
          "name": "2011",
          "value": 5800000
        }
      ]
    }
  ];


  readingData:Readings[] = [{
      "timestamp": "2017-05-25T17:31:25.268Z",
      "fuelVolume": 1.5,
      "speed": 85,
      "engineHp": 240,
      "engineRpm": 6300,
      "tirefrontleft": 34,
      "tirefrontright": 36,
      "tirerearleft": 29,
      "tirerearright": 34
      
  }];

  results: any[] = [{
    name: "",
    value: 0
  }];




  // options
  showXAxis = true;
  showYAxis = true;
  gradient = false;
  showLegend = false;
  showXAxisLabel = true;
  xAxisLabel = 'Time';
  showYAxisLabel = true;
  yAxisLabel = 'Data';

  colorScheme = {
    domain: ['#3F51B5', '#9C27B0', '#673AB7', '#2196F3']
  };

  allVehicles:Vehicles[];
  
  constructor(private vehicleService:CarTrackerService) {
    sessionStorage.setItem("currentContent","3");
    this.cols=[
      "fuelVolume",
      "speed",
      "engineHp",
      "engineRpm",
      "tires"
    ]
  }

  ngOnInit() {
    this.vehicleService.getHighAlerts().subscribe((vehicles) => {
      this.allVehicles = vehicles;
    });
  }

  onSubmitClicked(vin:string,time_number:number, time_frame:string, signal_param:any){
    console.log(vin+":"+time_number+" : "+time_frame+" : "+signal_param);
    if(vin == undefined){
      alert("Select Vehicle for plotting")
    }
    else if(time_number == undefined || time_frame == undefined || signal_param == undefined){
      alert("Time and Time Frame with signal type mandatory");
    }
    else{
      if(time_number <= 0){
        alert("Time can't be zero or negative");
      }
      else if((time_number >24 && time_frame == "hr" )||(time_number >1440 && time_frame == "min" )){
        alert("Time can't exceed 24 hours");
      }
      else{
        var _min = 0;
        if(time_frame == "hr"){
          _min = time_number*60;
          console.log("Hour : 0"," Minute : ",_min);
        }
        else if(time_frame == "min"){
          _min = time_number;
          console.log("Hour : 0"," Minute : ",_min);
        }
        this.vehicleService.getVehicleGraphReadings(vin , _min).subscribe((readings) => {
          this.readingData = readings;
          if(signal_param != "tires")
            this.initializeGraphData(this.readingData , signal_param);
          else
            this.initializeTireGraphData(this.readingData , signal_param);
        });
        
      }
    }
  }

  initializeGraphData( _readingData : Readings[] , _signal_param : any){
    this.results = [];
    _readingData.forEach(element => {
      var obj = {};
      var  tire_array : any[] = [];
      obj["name"] = element["timestamp"];
      obj["value"] = element[_signal_param];
      console.log(obj);
      this.results.push(obj);
    });
    this.showgraph = true;
    this.showgraph2d = false;
    console.log(this.results);
  }

  initializeTireGraphData( _readingData : Readings[] , _signal_param : any){
    this.results = [];
    var frontLeft = {}, frontRight = {}, rearLeft = {}, rearRight = {};
    var flSeries = [], frSeries = [], rlSeries = [], rrSeries = [];
    _readingData.forEach(element => {
      var flObj = {}, frObj = {}, rlObj = {}, rrObj = {};
      flObj["name"] = element["timestamp"];
      flObj["value"] = element["tirefrontleft"];
      flSeries.push(flObj);
      frObj["name"] = element["timestamp"];
      frObj["value"] = element["tirefrontright"];
      frSeries.push(frObj);
      rlObj["name"] = element["timestamp"];
      rlObj["value"] = element["tirerearleft"];
      rlSeries.push(rlObj);
      rrObj["name"] = element["timestamp"];
      rrObj["value"] = element["tirerearright"];
      rrSeries.push(rrObj);
    });
    frontLeft["name"] = "front left tire";
    frontLeft["series"] = flSeries;
    frontRight["name"] = "front right tire";
    frontRight["series"] = frSeries;
    rearLeft["name"] = "rear left tire";
    rearLeft["series"] = rlSeries;
    rearRight["name"] = "rear right tire";
    rearRight["series"] = rrSeries;
    this.results.push(frontLeft);
    this.results.push(frontRight);
    this.results.push(rearLeft);
    this.results.push(rearRight);
    this.showgraph = false;
    this.showgraph2d = true;
    console.log(this.results);
  }

}

interface Vehicles{
  vin:string,
  make:string,
  model:string
}

interface Readings{
  timestamp: string,
  fuelVolume: number,
  speed: number,
  engineHp: number,
  engineRpm: number,
  tirefrontleft:number,
  tirefrontright: number,
  tirerearleft: number,
  tirerearright: number
}