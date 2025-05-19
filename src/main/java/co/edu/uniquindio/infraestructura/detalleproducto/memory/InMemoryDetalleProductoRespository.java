package co.edu.uniquindio.infraestructura.detalleproducto.memory;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import co.edu.uniquindio.dominio.detalleproducto.DetalleProducto;
import co.edu.uniquindio.dominio.detalleproducto.DetalleProductoRepository;

public class InMemoryDetalleProductoRespository implements DetalleProductoRepository {
    public static InMemoryDetalleProductoRespository instancia;

    private Map<Integer, DetalleProducto> datos;

    private Integer ultimoId;

    public static InMemoryDetalleProductoRespository getInstancia() {
        if (instancia == null) {
            synchronized (InMemoryDetalleProductoRespository.class) {
                if (instancia == null) instancia = new InMemoryDetalleProductoRespository();
            }
        }
        
        return instancia;
    }

    private InMemoryDetalleProductoRespository() {
        datos = new HashMap<>();
        ultimoId = 0;
    }

    @Override
    public DetalleProducto guardar(DetalleProducto detalleProducto) {
        if (detalleProducto.getId() == null) {
            detalleProducto.setId(++ultimoId);
        } else if (datos.get(detalleProducto.getId()) == null) {
            return null;
        }

        datos.put(detalleProducto.getId(), detalleProducto);

        return detalleProducto;
    }

    @Override
    public Optional<DetalleProducto> hallar(Integer id) {
        return Optional.ofNullable(datos.get(id));
    }

    @Override
    public List<DetalleProducto> hallarTodos() {
        return new LinkedList<>(datos.values());
    }

    @Override
    public List<DetalleProducto> obtenerPorCarroCompras(Integer carroComprasId) {
        return datos.values().stream()
            .filter(dp -> dp.getCarroCompras().getId() == carroComprasId)
            .toList()
        ;
    }

    @Override
    public Boolean eliminar(Integer id) {
        return datos.remove(id) != null;
    }
}
