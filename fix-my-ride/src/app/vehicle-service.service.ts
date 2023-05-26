import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { VehicleServiceEntity } from './vehicle-service-entity';

@Injectable({
  providedIn: 'root'
})
export class VehicleServiceService {

  private vehicleServicesUrl = "http://localhost:8080/vehicle-service"

  constructor(private httpClient: HttpClient) { }

  getVehicleServices(): Observable<VehicleServiceEntity[]> {
    return this.httpClient.get<VehicleServiceEntity[]>(this.vehicleServicesUrl);
  }
}
