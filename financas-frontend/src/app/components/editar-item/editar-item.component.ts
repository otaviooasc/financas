import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Receita } from '../../models/receita-response.model';
import { ReceitaService } from '../../services/receita.service';

@Component({
  selector: 'app-editar-item',
  standalone: true,
  imports: [ReactiveFormsModule, RouterModule, FormsModule],
  providers: [ReceitaService],
  templateUrl: './editar-item.component.html',
  styleUrl: './editar-item.component.scss'
})
export class EditarItemComponent implements OnInit{
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

  ngOnInit(): void {
    this.receita = history.state['receita'];;

    this.receitaForm = this.fb.group({
      data: [this.receita ? this.receita.data : ''],
      saldoLiquido: [this.receita ? this.receita.saldoLiquido : 0],
      rendimentoAluguel: [this.receita ? this.receita.rendimentoAluguel : 0],
      rendimentoAplicacoes: [this.receita ? this.receita.rendimentoAplicacoes : 0],
      outros: [this.receita ? this.receita.outros : 0]
    });
  }

  onSubmit() {
    if (this.receitaForm.valid) {
      this.receita.data = this.receitaForm.value.data;
      this.receita.saldoLiquido = this.receitaForm.value.saldoLiquido;
      this.receita.rendimentoAluguel = this.receitaForm.value.rendimentoAluguel;
      this.receita.rendimentoAplicacoes = this.receitaForm.value.rendimentoAplicacoes;
      this.receita.outros = this.receitaForm.value.outros;

      this.receitaService.editarReceita(this.receita).subscribe({
        next: () => {
          this.toastr.success('Receita editada com sucesso.');
          this.router.navigate(['receita']);
        },
        error: (err) => {
          this.toastr.error('Erro ao inserir a receita.');
        }
      });
    }
  }

  voltarInicio():void {
    this.router.navigate(['receita']);
  }
}
