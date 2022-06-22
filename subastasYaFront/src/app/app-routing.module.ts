import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListaProductoComponent } from './producto/lista-producto.component';
import { DetalleProductoComponent } from './producto/detalle-producto.component';
import { EditarProductoComponent } from './producto/editar-producto.component';
import { IndexComponent } from './index/index.component';
import { LoginComponent } from './auth/login.component';
import { RegistroComponent } from './auth/registro.component';
import { ConfigurationComponent } from './configuration/configuration.component';
import { ProdGuardService as guard } from './guards/prod-guard.service';
import { SendEmailComponent } from './changepassword/send-email.component';
import { ChangePasswordComponent } from './changepassword/change-password.component';
import { CategoriasProductoComponent } from './categorias/categorias-producto.component';
import { UserListComponent } from './users/user-list.component';
import { UserEditProfileComponent } from './users/user-edit-profile.component';
import { CreateAnuncioComponent } from './anunciosUser/create-anuncio.component';
import { ListAnuncioComponent } from './anunciosUser/list-anuncio.component';
import { ListaAnunciosUserComponent } from './producto/lista-anuncios-user.component';
import { UserEditProfileAdminComponent } from './edit-users-admin/user-edit-profile.component';


const routes: Routes = [
  { path: '', component: IndexComponent },
  { path: 'login', component: LoginComponent },
  { path: 'registro', component: RegistroComponent },
  { path: 'send-email', component: SendEmailComponent },
  { path: 'change-password/:tokenPassword', component: ChangePasswordComponent },
  { path: 'lista', component: ListaProductoComponent, canActivate: [guard], data: {expectedRole: ['admin']} },
  { path: 'user/edit', component: UserEditProfileComponent, canActivate: [guard], data: {expectedRole: ['admin', 'user']} },
  { path: 'admin/user/edit/:username', component: UserEditProfileAdminComponent, canActivate: [guard], data: {expectedRole: ['admin']} },
  { path: 'anuncio/:id', component: DetalleProductoComponent, canActivate: [guard], data: {expectedRole: ['admin', 'user']} },
  { path: 'list/anuncio', component: ListAnuncioComponent, canActivate: [guard], data: {expectedRole: ['admin', 'user']} },
  { path: 'create/anuncio', component: CreateAnuncioComponent, canActivate: [guard], data: {expectedRole: ['admin', 'user']} },
  { path: 'config', component: ConfigurationComponent, canActivate: [guard], data: {expectedRole: ['admin']} },
  { path: 'lista-anuncios-usuario/:id', component: ListaAnunciosUserComponent, canActivate: [guard], data: {expectedRole: ['admin']} },
  { path: 'category/list', component: CategoriasProductoComponent, canActivate: [guard], data: {expectedRole: ['admin',]} },
  { path: 'users/list', component: UserListComponent, canActivate: [guard], data: {expectedRole: ['admin']} },
  { path: 'editar/:id', component: EditarProductoComponent, canActivate: [guard], data: {expectedRole: ['admin']} },
  { path: '**', redirectTo: '', pathMatch: 'full' },
];


@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
