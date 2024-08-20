import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, tap, throwError } from 'rxjs';
import { LoginResponse } from '../types/login-response.type';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private httpClient: HttpClient) { }

  login(email: string, password: string){
    return this.httpClient.post<LoginResponse>("http://localhost:8080/api/auth/login", {email, password}).pipe(
      tap((value) =>{
        sessionStorage.setItem("token", value.token)
      })
    );
  }

  cadastrar(name: string, email: string, password: string) {
    return this.httpClient.post("http://localhost:8080/api/auth/registrar", {name, email, password})
    .pipe(
      catchError(error => {
        // Tratar o erro aqui se necessário
        return throwError(error);
      })
    );
  }
}
