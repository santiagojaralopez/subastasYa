export class TipoProducto{
    id?: number = 0;
    nombre_tipo: string = '';
    descripcion: string = '';

    constructor(
         nombreTipo: string,
         descripcion: string){
             this.nombre_tipo = nombreTipo;
             this.descripcion = descripcion;;
         }
}