import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterModule } from '@angular/router';
import { ReceitaService } from '../../services/receita.service';
import { Receita } from '../../models/receita-response.model';

@Component({
  selector: 'app-receita',
  standalone: true,
  imports: [CommonModule, RouterModule],
  providers: [ReceitaService],
  templateUrl: './receita.component.html',
  styleUrl: './receita.component.scss'
})
export class ReceitaComponent implements OnInit{
  receita: Receita = new Receita();

  constructor(private receitaService: ReceitaService) {}

  ngOnInit(): void {
    this.receitaService.listarTodosReceita().subscribe(
      (data) => {
        Object.assign(this.receita, data);
        console.log(this.receita);
      }
    );
  }

}
