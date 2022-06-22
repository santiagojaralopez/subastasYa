import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListaProductoComponent } from './producto/lista-producto.component';
import { DetalleProductoComponent } from './producto/detalle-producto.component';
import { interceptorProvider } from './interceptors/prod-interceptor.service';

import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

// external
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { LoginComponent } from './auth/login.component';
import { RegistroComponent } from './auth/registro.component';
import { MenuComponent } from './menu/menu.component';
import { IndexComponent } from './index/index.component';
import { ConfigurationComponent } from './configuration/configuration.component';
import { SendEmailComponent } from './changepassword/send-email.component';
import { ChangePasswordComponent } from './changepassword/change-password.component';
import { CategoriasProductoComponent } from './categorias/categorias-producto.component';
import { FooterComponent } from './footer/footer.component';
import { UserListComponent } from './users/user-list.component';
import { UserEditProfileComponent } from './users/user-edit-profile.component';
import { CreateAnuncioComponent } from './anunciosUser/create-anuncio.component';
import { ListAnuncioComponent } from './anunciosUser/list-anuncio.component';
import { OffersComponent } from './offers/offers.component';



@NgModule({
  declarations: [
    AppComponent,
    ListaProductoComponent,
    DetalleProductoComponent,
    LoginComponent,
    RegistroComponent,
    MenuComponent,
    IndexComponent,
    ConfigurationComponent,
    SendEmailComponent,
    ChangePasswordComponent,
    CategoriasProductoComponent,
    FooterComponent,
    UserListComponent,
    UserEditProfileComponent,
    CreateAnuncioComponent,
    ListAnuncioComponent,
    OffersComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [interceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
