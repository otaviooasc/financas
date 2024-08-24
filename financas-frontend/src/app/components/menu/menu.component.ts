import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Usuario } from '../../models/usuario-response.model';

@Component({
  selector: 'app-menu',
  standalone: true,
  imports: [],
  templateUrl: './menu.component.html',
  styleUrl: './menu.component.scss'
})
export class MenuComponent {
  usuario!: Usuario;

  constructor(private http: HttpClient, private router: Router) {}

  ngOnInit(): void {
    this.recuperarUsuario();
  }

  recuperarUsuario() {
    const usuarioData = sessionStorage.getItem('usuario');
    if (usuarioData) {
      this.usuario = JSON.parse(usuarioData);
    }
  }

  onReceitaClick(){
    this.router.navigate(['/receita']);
  }
}
