import { TipoProducto } from "./tipoProducto";

export class Producto {
    id?: number;
    nombre: string;
    foto_producto: string;
    tipoproducto: TipoProducto;

    constructor(nombre: string, fotoProducto: string,tipoproducto: TipoProducto) {
        this.nombre = nombre;
        this.foto_producto = fotoProducto;
        this.tipoproducto = tipoproducto;
    }
}


