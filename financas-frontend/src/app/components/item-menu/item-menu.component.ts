import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Receita } from '../../models/receita-response.model';
import { Router, RouterModule } from '@angular/router';
import { ReceitaService } from '../../services/receita.service';

@Component({
  selector: 'app-item-menu',
  standalone: true,
  imports: [RouterModule],
  providers: [ReceitaService],
  templateUrl: './item-menu.component.html',
  styleUrl: './item-menu.component.scss'
})
export class ItemMenuComponent {
  @Input() receita!: Receita;
  @Output() deleteItem = new EventEmitter<Receita>();

  private receitaResponse!: Receita;

  constructor(private router: Router, private receitaService: ReceitaService){}

  detalhesReceita(id: String){
    this.receitaService.listarPorIdReceita(id).subscribe(
      (data) => {
        this.receitaResponse = data;
      }
    )
  }

  onDelete(receita: Receita) {
    this.deleteItem.emit(receita);
  }
}
