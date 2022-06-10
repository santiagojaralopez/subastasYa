import { TipoProducto } from "./tipoProducto";

export class Producto {
    id?: number = 0;
    nombre: string = '';
    tipoProdcucto: TipoProducto | undefined;

    constructor(nombre: string, tipoProducto: TipoProducto) {
        this.nombre = nombre;
        this.tipoProdcucto = tipoProducto;
    }
}


