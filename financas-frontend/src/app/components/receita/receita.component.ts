import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ReceitaService } from '../../services/receita.service';
import { Receita } from '../../models/receita-response.model';
import { ItemMenuComponent } from "../item-menu/item-menu.component";
import { AdicionarItemComponent } from '../adicionar-item/adicionar-item.component';
import { ToastrService } from 'ngx-toastr';
import { HttpErrorResponse } from '@angular/common/http';

@Component({
  selector: 'app-receita',
  standalone: true,
  imports: [CommonModule, RouterModule, ItemMenuComponent, AdicionarItemComponent],
  providers: [ReceitaService],
  templateUrl: './receita.component.html',
  styleUrl: './receita.component.scss'
})
export class ReceitaComponent implements OnInit{
  receitas: Receita[] = [];

  constructor(private receitaService: ReceitaService,
    private toastr: ToastrService
  ) {
    this.toastr.toastrConfig.timeOut = 700;
  }

  ngOnInit(): void {
    this.receitaService.listarTodosReceita().subscribe(
      (data) => {
        this.receitas = data;
      },
      (error: HttpErrorResponse) => {
        if(error.status == 404){
          this.toastr.error('Nenhuma receita encontrada!');
        } else {
          this.toastr.error('Sistema indisponível!');
        }
      }
    );
  }

  deleteReceita(receita: Receita){
    this.receitaService.delete(receita).subscribe(
      () => (this.receitas = this.receitas.filter((r) => r.id !== receita.id)),
      (error: HttpErrorResponse) => {
        if(error.status == 404){
          this.toastr.error('Nenhuma receita encontrada!');
        } else {
          this.toastr.error('Sistema indisponível!');
        }
      }
    );
  }
}
