import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListaProductoComponent } from './producto/lista-producto.component';
import { DetalleProductoComponent } from './producto/detalle-producto.component';
import { NuevoProductoComponent } from './producto/nuevo-producto.component';
import { EditarProductoComponent } from './producto/editar-producto.component';
import { IndexComponent } from './index/index.component';
import { LoginComponent } from './auth/login.component';
import { RegistroComponent } from './auth/registro.component';
import { ConfigurationComponent } from './configuration/configuration.component';
import { ProdGuardService as guard } from './guards/prod-guard.service';
import { CategoriasProductoComponent } from './categorias/categorias-producto.component';
import { UserListComponent } from './users/user-list.component';


const routes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registro', component: RegistroComponent },
  { path: 'lista', component: ListaProductoComponent, canActivate: [guard], data: {expectedRole: ['admin', 'user']} },
  { path: 'detalle/:id', component: DetalleProductoComponent, canActivate: [guard], data: {expectedRole: ['admin', 'user']} },
  { path: 'nuevo', component: NuevoProductoComponent, canActivate: [guard], data: {expectedRole: ['admin']} },
  { path: 'config', component: ConfigurationComponent, canActivate: [guard], data: {expectedRole: ['admin']} },
  { path: 'category/list', component: CategoriasProductoComponent, canActivate: [guard], data: {expectedRole: ['admin']} },
  { path: 'users/list', component: UserListComponent, canActivate: [guard], data: {expectedRole: ['admin']} },
  { path: 'editar/:id', component: EditarProductoComponent, canActivate: [guard], data: {expectedRole: ['admin']} },
  { path: '**', redirectTo: '', pathMatch: 'full' },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
