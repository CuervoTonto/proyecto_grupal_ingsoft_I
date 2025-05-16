package co.edu.uniquindio.aplicacion.detalleproducto;

import java.util.Optional;

import co.edu.uniquindio.dominio.detalleproducto.DetalleProducto;
import co.edu.uniquindio.dominio.detalleproducto.DetalleProductoRepository;

public class DetalleProductoService {
    // private DetalleProductoRepository repositorio;

    private CrearDetalleProductoUseCase crearDetalleProducto;
    private EliminarDetalleProductoUseCase eliminarDetalleProducto;
    private ActualizarDetalleProductoUseCase actualizarDetalleProducto;
    private HallarDetalleProductoUseCase hallarDetalleProducto;

    public DetalleProductoService(DetalleProductoRepository repositorio) {
        // this.repositorio = repositorio;
        crearDetalleProducto = new CrearDetalleProductoUseCase(repositorio);
        hallarDetalleProducto = new HallarDetalleProductoUseCase(repositorio);
        eliminarDetalleProducto = new EliminarDetalleProductoUseCase(repositorio);
        actualizarDetalleProducto = new ActualizarDetalleProductoUseCase(repositorio);
    }

    public Optional<DetalleProducto> hallarPorId(Integer id) {
        return hallarDetalleProducto.execute(id);
    }

    public DetalleProducto crear(DetalleProducto detalleProducto) {
        return crearDetalleProducto.execute(detalleProducto);
    }

    public DetalleProducto actualizar(DetalleProducto detalleProducto) {
        return actualizarDetalleProducto.execute(detalleProducto);
    }

    public Boolean eliminar(Integer id) {
        return eliminarDetalleProducto.execute(id);
    }
}
