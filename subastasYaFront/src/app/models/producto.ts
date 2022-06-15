import { TipoProducto } from "./tipoProducto";

export class Producto {
    id?: number = 0;
    nombre: string = '';
    fotoProducto: string;

    constructor(nombre: string, fotoProducto: string) {
        this.nombre = nombre;
        this.fotoProducto = fotoProducto;
    }
}


