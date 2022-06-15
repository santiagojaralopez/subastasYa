export class UpdateUsuarioDTO {
    id: number;
    nombre: string;
    apellido: string;
    nombreUsuario: string;
    email: string;
    tipoDocumento: string;
    numerodoc: string;
    fechanacto: Date;
    direccion: string;

    // tslint:disable-next-line: max-line-length
    constructor(nombre: string, apellido: string, numerodoc: string, fechanacto: Date, direccion: string, tipoDocumento: string, nombreUsuario: string, email: string) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.nombreUsuario = nombreUsuario;
        this.email = email;
        this.tipoDocumento = tipoDocumento;
        this.numerodoc = numerodoc;
        this.fechanacto = fechanacto;
        this.direccion = direccion;
    }
}
