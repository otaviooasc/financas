import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { ReceitaService } from '../../services/receita.service';
import { Receita } from '../../models/receita-response.model';

@Component({
  selector: 'app-adicionar-item',
  standalone: true,
  imports: [ReactiveFormsModule, RouterModule, FormsModule],
  providers: [ReceitaService],
  templateUrl: './adicionar-item.component.html',
  styleUrl: './adicionar-item.component.scss'
})
export class AdicionarItemComponent {
  receitaForm: FormGroup;
  private receita: Receita = new Receita();
  isPasswordVisible = false;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private toastr: ToastrService,
    private receitaService: ReceitaService
  ) {
    this.receitaForm = this.fb.group({
      data: ['', [Validators.required]],
      saldoLiquido: ['', [Validators.required]],
      rendimentoAluguel: ['', [Validators.required]],
      rendimentoAplicacoes: ['', [Validators.required]],
      outros: ['', [Validators.required]],
    });
  }

  onSubmit() {
    if (this.receitaForm.valid) {
      this.receita.data = this.receitaForm.value.data;
      this.receita.saldoLiquido = this.receitaForm.value.saldoLiquido;
      this.receita.rendimentoAluguel = this.receitaForm.value.rendimentoAluguel;
      this.receita.rendimentoAplicacoes = this.receitaForm.value.rendimentoAplicacoes;
      this.receita.outros = this.receitaForm.value.outros;

      this.receitaService.cadastrarReceita(this.receita).subscribe({
        next: () => {
          this.toastr.success('Receita inserida com sucesso.');
          this.router.navigate(['receita']);
        },
        error: (err) => {
          this.toastr.success('Erro ao inserir a receita.');
        }
      });
    }
  }

  voltarInicio():void {
    this.router.navigate(['receita']);
  }
}
