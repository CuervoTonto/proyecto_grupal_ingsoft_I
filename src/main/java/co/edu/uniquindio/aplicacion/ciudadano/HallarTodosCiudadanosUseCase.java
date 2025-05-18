package co.edu.uniquindio.aplicacion.ciudadano;

import java.util.List;

import co.edu.uniquindio.dominio.ciudadano.Ciudadano;
import co.edu.uniquindio.dominio.ciudadano.CiudadanoRepository;

public class HallarTodosCiudadanosUseCase {
    private CiudadanoRepository repositorio;

    public HallarTodosCiudadanosUseCase(CiudadanoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<Ciudadano> execute() {
        return repositorio.hallarTodos();
    }
}
