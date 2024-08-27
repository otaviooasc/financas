import { Component, Input } from '@angular/core';
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

  private receitaResponse!: Receita;

  constructor(private router: Router, private receitaService: ReceitaService){}

  detalhesReceita(id: String){
    this.receitaService.listarPorIdReceita(id).subscribe(
      (data) => {
        this.receitaResponse = data;
        console.log(this.receitaResponse);
      }
    )
  }
}
