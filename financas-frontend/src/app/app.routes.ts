import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { CadastrarComponent } from './components/cadastrar/cadastrar.component';
import { MenuComponent } from './components/menu/menu.component';
import { ReceitaComponent } from './components/receita/receita.component';
import { ItemMenuComponent } from './components/item-menu/item-menu.component';
import { AdicionarItemComponent } from './components/adicionar-item/adicionar-item.component';

export const routes: Routes = [
  {
    path: "",
    component: LoginComponent
  },
  {
    path: "cadastrar",
    component: CadastrarComponent
  },
  {
    path: "menu",
    component: MenuComponent
  },
  {
    path: "receita",
    component: ReceitaComponent
  },
  {
    path: "despesa-fixa",
    component: ReceitaComponent
  },
  {
    path: "despesa-variavel",
    component: ReceitaComponent
  },
  {
    path: "item-menu",
    component: ItemMenuComponent
  },
  {
    path: "receita/adicionar-item",
    component: AdicionarItemComponent
  }
];
