import { Component, OnInit } from '@angular/core';
import { TokenService } from '../service/token.service';
import { AnuncioService } from '../service/anuncio.service';
import { Anuncio } from '../models/anuncio';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';
import { OfferDTO } from '../models/offer-dto';

@Component({
  selector: 'app-index',
  templateUrl: './index.component.html',
  styleUrls: ['./index.component.css']
})
export class IndexComponent implements OnInit {

  anuncios: Anuncio[] = [];
  roles: string[] = [];
  isAdmin = false;

  isLogged = false;
  nombreUsuario = '';

  offerDto: OfferDTO = new OfferDTO();

  constructor(
    private anuncioService: AnuncioService,
    private tokenService: TokenService,
    private router: Router
  ) { }

  ngOnInit() {
    this.anuncioService.listaActivos().subscribe(
      data => this.anuncios = data
    );

    if (this.tokenService.getToken()) {
      this.isLogged = true;
    }

    this.roles = this.tokenService.getAuthorities();
    this.roles.forEach((rol) => {
      if (rol === 'ROLE_ADMIN') {
        this.isAdmin = true;
      }
    });
  }

  onOffer() {
    if (!this.isLogged) {
      this.router.navigate(['/login']);
    } else {
      Swal.fire({
        title: '<strong>Pujar por este producto</strong>',
        input: 'number',
        inputAttributes: { autocomplete: 'off' },
        inputLabel: '¿Cuánto quieres pujar por este anuncio?',
        inputValue: this.offerDto.offerValue,
        showCloseButton: true,
        showDenyButton: true,
        focusConfirm: false,
        confirmButtonText: 'Pujar',
        denyButtonText: 'Cancelar',
        inputValidator: (value) => {
          if (!value) {
            return 'Debes asignar un valor a tu puja!';
          } else {
            this.offerDto.offerValue = parseFloat(value);
          }
        }
      }).then((result) => {
        if (result.isConfirmed) {
          this.sureAboutOffering();
        } else if (result.isDenied) {
          Swal.fire('Se ha cancelado tu proceso de puja', '', 'info');
          this.offerDto.offerValue = undefined;
        }
      });
    }
  }

  sureAboutOffering() {
    Swal.fire({
      title: 'Confirmar puja',
      showDenyButton: true,
      confirmButtonText: 'Confirmar',
      denyButtonText: `Cancelar`,
    }).then((result) => {
      if (result.isConfirmed) {
        Swal.fire('Puja realizada!', 'Has pujado por un valor de ' + this.offerDto.offerValue, 'success');
        console.log(this.offerDto.offerValue);
      } else if (result.isDenied) {
        Swal.fire('Se ha cancelado tu proceso de puja', '', 'info');
        this.offerDto.offerValue = undefined;
      }
    });
  }
}
