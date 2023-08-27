import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders, HttpRequest } from '@angular/common/http';
import { AuthComponent } from '../auth/auth.component';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { LoginUser } from '../entity/login-user-entity';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  private loginUrl: string = 'http://localhost:8080/login';
  private httpClient: HttpClient;
  authComponent: AuthComponent;
  loginForm: FormGroup;


  constructor(httpClient: HttpClient, authComponent: AuthComponent) {
    this.httpClient = httpClient;
    this.authComponent = authComponent;
    this.loginForm = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    })
  }

  loginUser() {
    console.log("loginUser");
    if (this.loginForm.valid) {
      let isAuthenticated = false;
      const loginUserEntity: LoginUser = new LoginUser(this.loginForm.get('username')!.value, this.loginForm.get('password')!.value);
      this.httpClient.post<LoginUser>(this.loginUrl, loginUserEntity).subscribe({
        next(value) {
          isAuthenticated = true;
          console.log("next");
        },
        error(msg) {
          console.log(msg);
          
        },
        complete() {
          isAuthenticated = true;
          console.log("Completed authentication");
        }
      }
      );
      if(isAuthenticated){
        this.authComponent.authenticate();
        this.refreshPage();
      }

    }
  }

  refreshPage() {
    window.location.reload();
}

  authenticateUser(){
    this.authComponent.authenticate();
  }

  ngOnInit(): void {
  }
}
