import { Ciudad } from "./ciudad";
import { EstadoAnuncio } from "./estadoAnuncio";
import { Producto } from "./producto";
import { Usuario } from "./usuario";

export class Anuncio {
    id_anuncio: number;
    descripcion: string = '';
    fecha_inicio: Date | undefined;
    fecha_fin: Date | undefined;
    ciudad: Ciudad | undefined;
    usuario: Usuario | undefined;
    estado: EstadoAnuncio | undefined;
    valor: number = 0;
    producto: Producto | undefined;

    constructor(descripcion: string,
         usuario: Usuario,
         ciudad: Ciudad,producto: Producto, valor: number){
             this.descripcion = descripcion;
             this.usuario = usuario;
             this.ciudad = ciudad;
             this.producto = producto;
             this.valor = valor;
         }


}