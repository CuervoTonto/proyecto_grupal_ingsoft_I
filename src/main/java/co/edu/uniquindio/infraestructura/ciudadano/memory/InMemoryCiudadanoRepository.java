package co.edu.uniquindio.infraestructura.ciudadano.memory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import co.edu.uniquindio.dominio.ciudadano.Ciudadano;
import co.edu.uniquindio.dominio.ciudadano.CiudadanoRepository;
import co.edu.uniquindio.infraestructura.detalleproducto.memory.InMemoryDetalleProductoRespository;

public class InMemoryCiudadanoRepository implements CiudadanoRepository {
    public static InMemoryCiudadanoRepository instancia;

    private Map<Integer, Ciudadano> datos;

    private Integer ultimoId;

    public static InMemoryCiudadanoRepository getInstancia() {
        if (instancia == null) {
            synchronized (InMemoryDetalleProductoRespository.class) {
                if (instancia == null) instancia = new InMemoryCiudadanoRepository();
            }
        }
        
        return instancia;
    }

    private InMemoryCiudadanoRepository() {
        datos = new HashMap<>();
        ultimoId = 0;
    }

    @Override
    public Ciudadano guardar(Ciudadano ciudadano) {
        if (ciudadano.getId() == null) {
            ciudadano.setId(++ultimoId);
        } else {
            Ciudadano almecenado = datos.get(ciudadano.getId());

            if (almecenado == null) {
                return null;
            }
        }

        datos.put(ciudadano.getId(), ciudadano);

        return ciudadano;
    }

    @Override
    public Optional<Ciudadano> hallar(Integer id) {
        return Optional.ofNullable(datos.get(id));
    }

    @Override
    public List<Ciudadano> hallarTodos() {
        return datos.values().stream().toList();
    }
}
