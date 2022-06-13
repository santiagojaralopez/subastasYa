export class TipoProducto{
    id?: number = 0;
    nombreTipo: string = '';
    descripcion: string = '';

    constructor(
         nombreTipo: string,
         descripcion: string){
             this.nombreTipo = nombreTipo;
             this.descripcion = descripcion;;
         }
}