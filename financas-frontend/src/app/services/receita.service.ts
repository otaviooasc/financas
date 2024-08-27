import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Receita } from '../models/receita-response.model';

@Injectable({
  providedIn: 'root'
})
export class ReceitaService {

  constructor(private httpClient: HttpClient) { }

  listarTodosReceita() {
    const token = sessionStorage.getItem('token');
    const usuarioSessionStorage = sessionStorage.getItem('usuario');

    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`
    });

    let usuario = JSON.parse(usuarioSessionStorage!);

    const url = `http://localhost:8080/api/receita/listar/${usuario.id}`;
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

    const url = `http://localhost:8080/api/receita/listar-por-receita/${id}`;
    return this.httpClient.get<Receita>(url, {headers})
    .pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: any): Observable<never> {
    return throwError(() => new Error('Algo deu errado; por favor, tente novamente mais tarde.'));
  }
}
