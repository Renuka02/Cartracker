import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';

@Injectable()
export class CarTrackerService {

  constructor(public http:Http) { }

    getVehicles(){
      return this.http.get('http://localhost:8998/cartracker/readvehicles')
        .map(res => res.json());
    }

    getVehicleDetails(_vin){
      return this.http.get('http://localhost:8998/cartracker/readvehicles/'+_vin)
        .map(res => res.json());
    }

    getHighAlerts(){
      return this.http.get('http://localhost:8998/cartracker/gethighalerts')
      .map(res => res.json());
    }

    getHistoricalAlerts(_vin){
      return this.http.get('http://localhost:8998/cartracker/getreading/'+_vin)
      .map(res => res.json());
    }

    getGeoLocation(_vin){
      return this.http.get('http://localhost:8998/cartracker/getlocation/'+_vin)
      .map(res => res.json());
    }

    getVehicleGraphReadings(_vin , _min){
      return this.http.get('http://localhost:8998/cartracker/getreading/'+_vin+'/'+0+'/'+_min)
      .map(res=>res.json());
    }

}
