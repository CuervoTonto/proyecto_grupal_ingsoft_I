package co.edu.uniquindio.aplicacion.ciudadano;

import java.util.Optional;

import co.edu.uniquindio.dominio.ciudadano.Ciudadano;
import co.edu.uniquindio.dominio.ciudadano.CiudadanoRepository;

/**
 * hallar un ciudadano en base a un id dado
 */
public class HallarCiudadanoUseCase {
    private CiudadanoRepository repositorio;

    public HallarCiudadanoUseCase(CiudadanoRepository repositorio) {
        this.repositorio = repositorio;
    }

    /**
     * busca un usuario dado su id
     * @param id id del usuario
     * @return el usuario que coincide con el id
     */
    public Optional<Ciudadano> execute(Integer id) {
        return this.repositorio.hallar(id);
    }
}
