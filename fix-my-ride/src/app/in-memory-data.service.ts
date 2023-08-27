import { Injectable } from '@angular/core';
import { InMemoryDbService } from 'angular-in-memory-web-api'
import { VehicleServiceEntity } from './entity/vehicle-service-entity';
@Injectable({
  providedIn: 'root'
})
export class InMemoryDataService implements InMemoryDbService{

  createDb(){
    const vehicleServices = [
      {id: 1, name: "Fast Repair", country: "Poland", city: "Warsaw", street: "Warsawska 9", postalCode: 12345},
      {id: 2, name: "Tuning Service", country: "Germany", city: "Munchen", street: "Karola 4", postalCode: 67890}
    ];
    return {vehicleServices}
  }

  constructor() { }
}
