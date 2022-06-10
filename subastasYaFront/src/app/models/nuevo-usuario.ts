export class NuevoUsuario {
    nombres: string;
    apellidos: string;
    tipoDocumento: string;
    numeroDocumento: string;
    fechaNacimiento: Date;
    email: string;
    nombreUsuario: string;
    password: string;
    departamento: string;
    direccion: string;

    constructor(nombres: string, apellidos: string, tipoDocumento: string, numeroDocumento: string, fechaNacimiento: Date, email: string, nombreUsuario: string, password: string, departamento: string, direccion: string) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.departamento = departamento;
        this.direccion = direccion;
    }
}
