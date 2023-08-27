import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { Injectable } from '@angular/core';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
@Injectable({
  providedIn: 'root'
})
export class AuthComponent {
  isAuthenticated: boolean;
  
  constructor() {
    this.isAuthenticated = false;
  }

  authenticate(){
    this.isAuthenticated = true;
  }

  logout(){
    this.isAuthenticated = false;
  }
}
