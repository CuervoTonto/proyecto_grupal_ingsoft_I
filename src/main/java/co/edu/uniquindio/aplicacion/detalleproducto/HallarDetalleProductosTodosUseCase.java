package co.edu.uniquindio.aplicacion.detalleproducto;

import java.util.List;

import co.edu.uniquindio.dominio.detalleproducto.DetalleProducto;
import co.edu.uniquindio.dominio.detalleproducto.DetalleProductoRepository;

public class HallarDetalleProductosTodosUseCase {
    private DetalleProductoRepository repositorio;

    public HallarDetalleProductosTodosUseCase(DetalleProductoRepository repositorio) {
        this.repositorio = repositorio;
    }

    public List<DetalleProducto> execute() {
        return repositorio.hallarTodos();
    }
}
