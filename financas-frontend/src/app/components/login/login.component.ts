import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { LoginService } from '../../services/login.service';
import { Toast, ToastrService } from 'ngx-toastr';
import { CadastrarComponent } from '../cadastrar/cadastrar.component';
import { Usuario } from '../../models/usuario-response.model';

@Component({
  selector: 'app-default-login-layout',
  standalone: true,
  imports: [ReactiveFormsModule, CadastrarComponent, RouterModule],
  providers: [LoginService],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  loginForm: FormGroup;
  isPasswordVisible = false;
  usuario: Usuario = new Usuario();

  constructor(private fb: FormBuilder,
    private router: Router,
    private loginService: LoginService,
    private toastr: ToastrService
  ) {
    this.loginForm = this.fb.group({
      username: ['', [Validators.required]],
      password: ['', [Validators.required]]
    });
  }

  onSubmit() {
    if (this.loginForm.valid) {
      this.loginService.login(this.loginForm.value.username, this.loginForm.value.password).subscribe({
        next: () => {
          this.loginService.getUsuarioByEmail(this.loginForm.value.username).subscribe(
            (data) => {
              this.usuario.id = data.id;
              this.usuario.nome = data.nome;
              this.usuario.email = data.email;
              sessionStorage.setItem('usuario', JSON.stringify(this.usuario));
              this.toastr.success('Olá '+ this.usuario.nome);
            },
            (error) => {
              this.toastr.error('Usuário ou senha invalidos!');
          });

          this.router.navigate(['/menu']);
        },
        error: (err) => {
          this.toastr.error('Usuário ou senha invalidos!');
        }
      });
    }
  }

  togglePasswordVisibility(): void {
    this.isPasswordVisible = !this.isPasswordVisible;
    const passwordField = document.getElementById('password') as HTMLInputElement;
    passwordField.type = this.isPasswordVisible ? 'text' : 'password';
  }
}
