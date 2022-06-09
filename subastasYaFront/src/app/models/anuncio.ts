import { Ciudad } from "./ciudad";
import { Departamento } from "./departamento";
import { EstadoAnuncio } from "./estadoAnuncio";
import { Producto } from "./producto";
import { Usuario } from "./usuario";

export class Anuncio {
    id_anuncio?: number = 0;
    descripcion: string = '';
    fecha_inicio: Date | undefined;
    fecha_fin: Date | undefined;
    ciudad: Ciudad | undefined;
    departamento: Departamento | undefined;
    usuario: Usuario | undefined;
    estado: EstadoAnuncio | undefined;
    valor: number = 0;
    producto: Producto | undefined;

    constructor(descripcion: string,
         fecha_inicio: Date,fecha_fin:Date,usuario: Usuario,estado: EstadoAnuncio,
         ciudad: Ciudad,departamento: Departamento,producto: Producto, valor: number){
             this.descripcion = descripcion;
             this.fecha_inicio = fecha_inicio;
             this.fecha_fin = fecha_fin;
             this.usuario = usuario;
             this.estado = estado;
             this.ciudad = ciudad;
             this.departamento = departamento;
             this.producto = producto;
             this.valor = valor;
         }


}