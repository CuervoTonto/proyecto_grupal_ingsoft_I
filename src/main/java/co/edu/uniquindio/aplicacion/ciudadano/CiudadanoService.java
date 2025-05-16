package co.edu.uniquindio.aplicacion.ciudadano;

import java.util.Optional;

import co.edu.uniquindio.dominio.ciudadano.Ciudadano;
import co.edu.uniquindio.dominio.ciudadano.CiudadanoRepository;

public class CiudadanoService {
    // private CiudadanoRepository repositorio;

    private CrearCiudadadoUseCase crearCiudadado;
    private HallarCiudadanoUseCase hallarCiudadano;

    public CiudadanoService(CiudadanoRepository repositorio) {
        // this.repositorio = repositorio;
        this.crearCiudadado = new CrearCiudadadoUseCase(repositorio);
        this.hallarCiudadano = new HallarCiudadanoUseCase(repositorio);
    }

    public Ciudadano crear(Ciudadano ciudadano) {
        return crearCiudadado.execute(ciudadano);
    }

    public Optional<Ciudadano> hallarPorId(Integer id) {
        return hallarCiudadano.execute(id);
    }
}
