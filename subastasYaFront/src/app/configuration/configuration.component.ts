import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Configuracion } from '../models/configuracion';
import { ConfiguracionService } from '../service/configuracion.service';
import { TokenService } from '../service/token.service';
import Swal from 'sweetalert2';



@Component({
  selector: 'app-configuration',
  templateUrl: './configuration.component.html',
  styleUrls: ['./configuration.component.css']
})
export class ConfigurationComponent implements OnInit {

  isLogged = false;
  nombreUsuario = '';

  edad: number;
  duracion: number;
  ofertas: number;
  anuncios: number;

  nuevaEdad: number;
  nuevaDuracion: number;
  nuevaOfertas: number;
  nuevaAnuncios: number;


  constructor(
    private tokenService: TokenService,
    private router: Router,
    private configuracionService: ConfiguracionService
  ) { }

  ngOnInit() {
    if (this.tokenService.getToken()) {
      this.isLogged = true;
      this.nombreUsuario = this.tokenService.getUserName();
    } else {
      this.isLogged = false;
      this.nombreUsuario = '';
    }
    this.configuracionService.getEdad().subscribe(
      data => this.edad = data.valor
    )
    this.configuracionService.getDuracion().subscribe(
      data => this.duracion = data.valor
    )
    this.configuracionService.getOfertas().subscribe(
      data => this.ofertas = data.valor
    )
    this.configuracionService.getAnuncios().subscribe(
      data => this.anuncios = data.valor
    )
  }

  onUpdate(){
    let nuevaEdadC = new Configuracion("edad_registro",Number(this.nuevaEdad))
    console.log(nuevaEdadC)
    this.configuracionService.updateConfiguration(1,nuevaEdadC).subscribe();

    let nuevaDuracionC = new Configuracion("duracion_dias_anuncio",Number(this.nuevaDuracion))
    console.log(nuevaDuracionC)
    this.configuracionService.updateConfiguration(2,nuevaDuracionC).subscribe();
    
    let nuevaOfertasC = new Configuracion("maximo_ofertas",Number(this.nuevaOfertas))
    console.log(nuevaOfertasC)
    this.configuracionService.updateConfiguration(3,nuevaOfertasC).subscribe();

    let nuevaAnunciosC = new Configuracion("maximo_anuncios",Number(this.nuevaAnuncios))
    console.log(nuevaAnunciosC)
    this.configuracionService.updateConfiguration(4,nuevaAnunciosC).subscribe();

    Swal.fire(
      'Éxito',
      'Su información ha sido actualizada con éxito',
      'success'
    );
  }
  onLogOut(): void {
    this.tokenService.logOut();
    window.location.reload();
    this.router.navigate(['/']);
  }

}
