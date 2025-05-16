package co.edu.uniquindio.infraestructura.detalleproducto.memory;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import co.edu.uniquindio.dominio.detalleproducto.DetalleProducto;
import co.edu.uniquindio.dominio.detalleproducto.DetalleProductoRepository;

public class InMemoryDetalleProductoRespository implements DetalleProductoRepository {
    public static final InMemoryDetalleProductoRespository INSTANCE = new InMemoryDetalleProductoRespository();

    private Map<Integer, DetalleProducto> datos;

    private Integer ultimoId;

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
    public Boolean eliminar(Integer id) {
        return datos.remove(id) != null;
    }
}
