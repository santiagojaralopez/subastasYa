import { Component, OnInit } from '@angular/core';
import { Anuncio } from '../models/anuncio';
import { ActivatedRoute, Router } from '@angular/router';
import { AnuncioService } from '../service/anuncio.service';
import { Usuario } from '../models/usuario';
import { UsuarioService } from '../service/usuario.service';

@Component({
  selector: 'app-lista-anuncios-user',
  templateUrl: './lista-anuncios-user.component.html',
  styleUrls: ['./lista-anuncios-user.component.css']
})
export class ListaAnunciosUserComponent implements OnInit {

  anuncios: Anuncio[] = [];
  usuarios: Usuario[] = [];
  usuario: Usuario;
  

  constructor(
    private anuncioService: AnuncioService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private usuarioService: UsuarioService
  ) { }

  async ngOnInit() {
    const id = this.activatedRoute.snapshot.params.id;
    this.usuarioService.lista().subscribe(
      data => this.usuarios = data
    );
    await new Promise(f => setTimeout(f, 100));
    this.usuario = this.findUserByUserById(id);  
    await new Promise(f => setTimeout(f, 100));
    this.anuncioService.listByUser(this.usuario.nombreUsuario).subscribe(
      data => this.anuncios = data
    );
  }

  findUserByUserById(id: number) {
    console.log('entre')
    let user = null;
    this.usuarios.forEach(element=>{
      if(element.id == id){
        user = element;
        console.log(element)
      }
    });
    return user;
  }

}


