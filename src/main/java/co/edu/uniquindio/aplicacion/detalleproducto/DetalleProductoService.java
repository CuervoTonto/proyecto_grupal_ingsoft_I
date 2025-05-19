package co.edu.uniquindio.aplicacion.detalleproducto;

import java.util.List;
import java.util.Optional;

import co.edu.uniquindio.dominio.detalleproducto.DetalleProducto;
import co.edu.uniquindio.dominio.detalleproducto.DetalleProductoRepository;

public class DetalleProductoService {
    // private DetalleProductoRepository repositorio;

    private CrearDetalleProductoUseCase crearDetalleProducto;
    private EliminarDetalleProductoUseCase eliminarDetalleProducto;
    private ActualizarDetalleProductoUseCase actualizarDetalleProducto;
    private HallarDetalleProductoUseCase hallarDetalleProducto;
    private EliminarDetalleProductoPorCarroComprasUseCase eliminarDetalleProductoPorCarroCompras;
    private HallarDetalleProductoPorCarroComprasUseCase hallarDetalleProductoPorCarroCompras;
    private HallarDetalleProductosTodosUseCase hallarDetalleProductosTodos;

    public DetalleProductoService(DetalleProductoRepository repositorio) {
        // this.repositorio = repositorio;
        crearDetalleProducto = new CrearDetalleProductoUseCase(repositorio);
        hallarDetalleProducto = new HallarDetalleProductoUseCase(repositorio);
        eliminarDetalleProducto = new EliminarDetalleProductoUseCase(repositorio);
        actualizarDetalleProducto = new ActualizarDetalleProductoUseCase(repositorio);
        eliminarDetalleProductoPorCarroCompras = new EliminarDetalleProductoPorCarroComprasUseCase(repositorio);
        hallarDetalleProductoPorCarroCompras = new HallarDetalleProductoPorCarroComprasUseCase(repositorio);
        hallarDetalleProductosTodos = new HallarDetalleProductosTodosUseCase(repositorio);
    }

    public Optional<DetalleProducto> hallarPorId(Integer id) {
        return hallarDetalleProducto.execute(id);
    }

    public List<DetalleProducto> hallarTodos() {
        return hallarDetalleProductosTodos.execute();
    }

    public List<DetalleProducto> hallarPorCarroCompra(Integer carroCompraId) {
        return hallarDetalleProductoPorCarroCompras.execute(carroCompraId);
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

    public Boolean elimnarPorCarroCompras(Integer carroComprasId) {
        return eliminarDetalleProductoPorCarroCompras.execute(carroComprasId);
    }
}
