export class TipoProducto{
    idtipo_producto?: number;
    nombre_tipo: string = '';
    descripcion: string = '';

    constructor(
         nombreTipo: string,
         descripcion: string){
             this.nombre_tipo = nombreTipo;
             this.descripcion = descripcion;;
         }
}