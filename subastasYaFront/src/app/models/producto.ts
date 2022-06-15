import { TipoProducto } from "./tipoProducto";

export class Producto {
    id?: number = 0;
    nombre: string = '';
    tipoProdcucto: TipoProducto | undefined;
    //fotoProducto: string= '';

    constructor(nombre: string, tipoProducto: TipoProducto) {
        this.nombre = nombre;
        this.tipoProdcucto = tipoProducto;
        //this.fotoProducto=fotoProducto;
    }
}


