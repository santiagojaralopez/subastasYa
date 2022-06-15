import { EstadoUsuario } from './estadoUsuario';
import { RoleNombre } from './RolNombre';

export class Usuario {
    id?: number;
    nombre: string;
    apellido = '';
    numerodoc = '';
    fechanacto: Date | undefined;
    direccion = '';
    estadoUsuario: EstadoUsuario | undefined;
    tipoDocumento: string | undefined;
    nombreUsuario = '';
    email = '';
    password = '';
    roles: RoleNombre[] = [];

    constructor(nombre: string, apellido: string, numerodoc: string,
                fechanacto: Date, direccion: string, estadoUsuario: EstadoUsuario,
                tipoDocumento: string, nombreUsuario: string, email: string,
                password: string, roles: RoleNombre[]) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.numerodoc = numerodoc;
            this.fechanacto = fechanacto;
            this.direccion = direccion;
            this.estadoUsuario = estadoUsuario;
            this.tipoDocumento = tipoDocumento;
            this.nombreUsuario = nombreUsuario;
            this.email = email;
            this.password = password;
            this.roles = roles;
        }
}
