package co.edu.uniquindio.dominio.ciudadano;

import java.util.List;
import java.util.Optional;

public interface CiudadanoRepository {
    public Ciudadano guardar(Ciudadano ciudadano);
    public Optional<Ciudadano> hallar(Integer id);
    public List<Ciudadano> hallarTodos();
}
