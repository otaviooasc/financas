import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, tap, throwError } from 'rxjs';
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

  cadastrar(nome: string, email: string, password: string) {
    return this.httpClient.post("http://localhost:8080/api/auth/registrar", {nome, email, password})
    .pipe(
      catchError(this.handleError)
    );
  }

  getUsuarioByEmail(email: string): Observable<any> {
    const token = sessionStorage.getItem('token');

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    const url = `${"http://localhost:8080/api/usuario/listar/email"}/${email}`;
    return this.httpClient.get<any>(url, { headers })
    .pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: any): Observable<never> {
    return throwError(() => error);
  }
}
