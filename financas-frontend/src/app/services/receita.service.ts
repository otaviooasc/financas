import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Receita } from '../models/receita-response.model';

@Injectable({
  providedIn: 'root'
})
export class ReceitaService {
  private apiUrl = 'http://localhost:8080/api/receita';

  constructor(private httpClient: HttpClient) { }

  listarTodosReceita() {
    const token = sessionStorage.getItem('token');
    const usuarioSessionStorage = sessionStorage.getItem('usuario');

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    let usuario = JSON.parse(usuarioSessionStorage!);

    const url = `${this.apiUrl}/listar/${usuario.id}`;
    return this.httpClient.get<Receita[]>(url, {headers})
    .pipe(
      catchError(this.handleError)
    );
  }

  listarPorIdReceita(id: String) {
    const token = sessionStorage.getItem('token');

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    const url = `${this.apiUrl}/listar-por-receita/${id}`;
    return this.httpClient.get<Receita>(url, {headers})
    .pipe(
      catchError(this.handleError)
    );
  }

  cadastrarReceita(receita: Receita) {
    const token = sessionStorage.getItem('token');
    const usuarioSessionStorage = sessionStorage.getItem('usuario');

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    let usuario = JSON.parse(usuarioSessionStorage!);

    const url = `${this.apiUrl}/criar/${usuario.id}`;
    return this.httpClient.post(url, receita, {headers})
    .pipe(
      catchError(this.handleError)
    );
  }

  delete(receita: Receita){
    const token = sessionStorage.getItem('token');
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    const url = `${this.apiUrl}/deletar/${receita.id}`;
    return this.httpClient.delete(url, {headers})
    .pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: any): Observable<never> {
    return throwError(() => error);
  }
}
