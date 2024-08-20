import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { LoginService } from '../../services/login.service';
import { Toast, ToastrService } from 'ngx-toastr';
import { CadastrarComponent } from '../cadastrar/cadastrar.component';

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
    console.log(this.loginForm.value);
    if (this.loginForm.valid) {
      // Lógica de autenticação aqui
      this.loginService.login(this.loginForm.value.username, this.loginForm.value.password).subscribe({
        next: () => {
          // Exibir mensagem de sucesso
          this.toastr.success('Login efetuado com sucesso!');
        },
        error: (err) => {
          // Exibir mensagem de erro
          this.toastr.error('Usuário ou senha invalidos!');
        }
      });
      //this.router.navigate(['/listar-contas']);
    }
  }

  togglePasswordVisibility(): void {
    this.isPasswordVisible = !this.isPasswordVisible;
    const passwordField = document.getElementById('password') as HTMLInputElement;
    passwordField.type = this.isPasswordVisible ? 'text' : 'password';
  }
}
