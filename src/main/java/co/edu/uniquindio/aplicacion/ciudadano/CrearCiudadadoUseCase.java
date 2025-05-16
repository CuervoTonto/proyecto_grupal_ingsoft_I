package co.edu.uniquindio.aplicacion.ciudadano;

import co.edu.uniquindio.dominio.ciudadano.Ciudadano;
import co.edu.uniquindio.dominio.ciudadano.CiudadanoRepository;

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
     * @param ciudadano informacion del ciudadano
     * @return ciudadano creado
     */
    public Ciudadano execute(Ciudadano ciudadano) {
        return this.repositorio.guardar(ciudadano);
    }
}
