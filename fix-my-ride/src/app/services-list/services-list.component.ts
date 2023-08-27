import { Component, OnInit } from '@angular/core';
import { VehicleServiceService } from '../vehicle-service.service';
import { VehicleServiceEntity } from '../entity/vehicle-service-entity';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-services-list',
  templateUrl: './services-list.component.html',
  styleUrls: ['./services-list.component.css']
})
export class ServicesListComponent implements OnInit {
  vehicleServices: VehicleServiceEntity[] = [];

  constructor(private vehicleService: VehicleServiceService) { }

  ngOnInit(){
    this.getVehicleServices().subscribe(vehicleServices => this.vehicleServices = vehicleServices);
  }

  private getVehicleServices(): Observable<VehicleServiceEntity[]>{
    console.log('Requesting vehicle services');
    return this.vehicleService.getVehicleServices();
  }
}
