package co.edu.cue.subastasYa.security.enums;


public enum TipoDocumento {
    PASAPORTE("Pasaporte"),
    CEDULA_DE_CIUDADANIA("Cedula de Ciudadania"),
    CEDULA_DE_EXTRANJERIA("Cedula de Extranjeria");

    private final String text;

    TipoDocumento(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
