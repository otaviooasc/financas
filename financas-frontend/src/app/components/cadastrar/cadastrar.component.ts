import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { LoginService } from '../../services/login.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-cadastrar',
  standalone: true,
  imports: [ReactiveFormsModule, RouterModule],
  providers: [LoginService],
  templateUrl: './cadastrar.component.html',
  styleUrl: './cadastrar.component.scss',
})
export class CadastrarComponent {
  cadastrarForm: FormGroup;
  isPasswordVisible = false;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private loginService: LoginService,
    private toastr: ToastrService
  ) {
    this.cadastrarForm = this.fb.group({
      username: ['', [Validators.required]],
      email: ['', [Validators.required]],
      password: ['', [Validators.required]],
    });
  }

  onSubmit() {
    console.log(this.cadastrarForm.value);
    if (this.cadastrarForm.valid) {
      // Lógica de autenticação aqui
      this.loginService
        .cadastrar(
          this.cadastrarForm.value.username,
          this.cadastrarForm.value.email,
          this.cadastrarForm.value.password
        )
        .subscribe({
          next: () => {
            // Exibir mensagem de sucesso
            this.toastr.success('Usuário registrado com sucesso!');
            this.router.navigate(['']);
          },
          error: (err) => {
            // Exibir mensagem de erro
            this.toastr.error('Erro ao registrar usuário!');
          },
        });
    }
  }

  togglePasswordVisibility(): void {
    this.isPasswordVisible = !this.isPasswordVisible;
    const passwordField = document.getElementById(
      'password'
    ) as HTMLInputElement;
    passwordField.type = this.isPasswordVisible ? 'text' : 'password';
  }
}
