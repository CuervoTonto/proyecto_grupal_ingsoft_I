package co.edu.uniquindio.infraestructura.producto.memory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import co.edu.uniquindio.dominio.producto.Producto;
import co.edu.uniquindio.dominio.producto.ProductoRepository;

public class InMemoryProductoRepository implements ProductoRepository {
    public static InMemoryProductoRepository instancia;

    private Map<Integer, Producto> datos;

    private Integer ultimoId;

    public static InMemoryProductoRepository getInstancia() {
        if (instancia == null) {
            synchronized (InMemoryProductoRepository.class) {
                if (instancia == null) instancia = new InMemoryProductoRepository();
            }
        }
        
        return instancia;
    }

    private InMemoryProductoRepository() {
        datos = new HashMap<>();
        ultimoId = 0;
    }

    @Override
    public Producto guardar(Producto producto) {
        if (producto.getId() == null) {
            producto.setId(++ultimoId);
        } else if (datos.get(producto.getId()) == null) {
            return null;
        }

        datos.put(producto.getId(), producto);

        return producto;
    }

    @Override
    public Optional<Producto> hallar(Integer id) {
        return Optional.ofNullable(datos.get(id));
    }

    @Override
    public List<Producto> hallarProductosPorNombre(String nombre) {
        return datos.values().stream()
            .filter(p -> p.getNombre().matches(".*" + nombre + ".*"))
            .toList()
        ;
    }

    @Override
    public List<Producto> hallarTodos() {
        return new LinkedList<>(datos.values());
    }
}
