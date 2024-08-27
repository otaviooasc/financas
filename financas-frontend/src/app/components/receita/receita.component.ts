import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ReceitaService } from '../../services/receita.service';
import { Receita } from '../../models/receita-response.model';
import { ItemMenuComponent } from "../item-menu/item-menu.component";

@Component({
  selector: 'app-receita',
  standalone: true,
  imports: [CommonModule, RouterModule, ItemMenuComponent],
  providers: [ReceitaService],
  templateUrl: './receita.component.html',
  styleUrl: './receita.component.scss'
})
export class ReceitaComponent implements OnInit{
  receitas: Receita[] = [];

  constructor(private receitaService: ReceitaService) {}

  ngOnInit(): void {
    this.receitaService.listarTodosReceita().subscribe(
      (data) => {
        this.receitas = data;
      }
    );
  }

}
