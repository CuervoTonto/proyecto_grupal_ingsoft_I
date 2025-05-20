package co.edu.uniquindio.aplicacion.ciudadano;

import co.edu.uniquindio.dominio.ciudadano.Ciudadano;
import co.edu.uniquindio.dominio.ciudadano.CiudadanoRepository;
import co.edu.uniquindio.dominio.ciudadano.CrearCiudadanoData;

/**
 * crea un ciudadano
 */
public class CrearCiudadadoUseCase {
    private CiudadanoRepository repositorio;
    
    public CrearCiudadadoUseCase(CiudadanoRepository repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * crea un ciudadano
     * @param datos informacion del ciudadano
     * @return ciudadano creado
     */
    public Ciudadano execute(CrearCiudadanoData datos) {
        Ciudadano ciudadano = new Ciudadano();
        ciudadano.setCedula(datos.cedula());
        ciudadano.setNombre(datos.nombre());
        ciudadano.setApellido(datos.apellido());
        ciudadano.setEmail(datos.email());
        ciudadano.setTelefono(datos.telefono());
        ciudadano.setEstado(datos.estado());

        return this.repositorio.guardar(ciudadano);
    }
}
