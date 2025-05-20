package co.edu.uniquindio.dominio.ciudadano;

public record CrearCiudadanoData(
    String cedula,
    String nombre,
    String apellido,
    String email,
    String telefono
) {
    public CiudadanoEstado estado() {
        return CiudadanoEstado.ACTIVO;
    }
}
