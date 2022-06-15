import { TipoProducto } from "./tipoProducto";

export class Producto {
<<<<<<< HEAD
    id?: number = 0;
    nombre: string = '';
    tipoProdcucto: TipoProducto | undefined;
    //fotoProducto: string= '';
=======
    id?: number;
    nombre: string;
    fotoProducto: string;
>>>>>>> santiago-gallego

    constructor(nombre: string, fotoProducto: string) {
        this.nombre = nombre;
<<<<<<< HEAD
        this.tipoProdcucto = tipoProducto;
        //this.fotoProducto=fotoProducto;
=======
        this.fotoProducto = fotoProducto;
>>>>>>> santiago-gallego
    }
}


