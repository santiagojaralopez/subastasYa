import { Anuncio } from './anuncio';
import { Usuario } from './usuario';

export class Offer {
    id_oferta: number;
    offerValue: number;
    bidderUser: Usuario;
    anuncio: Anuncio;
}
